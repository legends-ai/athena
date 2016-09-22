package ai.legends.athena

import org.apache.spark.{ SparkConf, SparkContext }
import com.datastax.spark.connector._

import ai.legends.athena.cassandra.CassandraMatchSum
import ai.legends.athena.sum.MatchSumRow
import ai.legends.athena.sum.Permuter

object Main {
  def main(args: Array[String]) {
    val cassandraHost = if (args.length > 0) args(0) else "127.0.0.1"

    val conf = new SparkConf(true).set("spark.cassandra.connection.host", cassandraHost)
    val sc = new SparkContext(conf)
    val rdd = sc.cassandraTable[CassandraMatch]("athena_out", "matches")

    // Ranks and match objects
    val matches = rdd.map(x => (x.toMatch(), x.rank))

    // Permutations of match sum rows

    // Disjoint set
    val permutations = Permuter.groupPermutations(Permuter.permuteMatches(matches))

    // Additional permutations
    val fullPermutations = permutations.union(Permuter.additionalPermutations(permutations))

    // Convert to Cassandra equivalent
    val matchSums = fullPermutations.map(matchSumRow => CassandraMatchSum(matchSumRow))
    matchSums.saveToCassandra("athena_out", "match_sums", AllColumns)

    println("Wrote " + matchSums.count() + " match sums.")
  }
}
