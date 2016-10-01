package ai.legends.athena

import org.apache.spark.{ SparkConf, SparkContext }
import com.datastax.spark.connector._

import ai.legends.athena.cassandra.CassandraMatchSum
import ai.legends.athena.sum.Permuter
import org.json4s._

object Main {
  def main(args: Array[String]) = {

    val conf = new SparkConf(true)
    val sc = new SparkContext(conf)
    var rdd = sc.cassandraTable[CassandraMatch]("athena", "matches")

    if (args.length != 0) {
      val patches = args(0).split(",")
      rdd = rdd.where("patch IN ?", patches)
    }

    // Ranks and match objects
    val matches = rdd.map(x => {
      try {
        (x.toMatch(), x.rank)
      } catch {
        case _: MappingException => null
      }
    }).filter(_ != null)

    // Permutations of match sum rows

    // Disjoint set
    val permutations = Permuter.groupPermutations(Permuter.permuteMatches(matches))

    // Additional permutations
    val fullPermutations = permutations.union(Permuter.additionalPermutations(permutations))

    // Convert to Cassandra equivalent
    val matchSums = fullPermutations.map(matchSumRow => CassandraMatchSum(matchSumRow))
    matchSums.saveToCassandra("athena_out", "match_sums", AllColumns)

    println("Wrote " + matchSums.count() + " match sums.")

    // stop spark context when we are done
    sc.stop()
  }
}
