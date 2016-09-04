package ai.legends.athena

import org.apache.spark.{ SparkConf, SparkContext }
import com.datastax.spark.connector._

import ai.legends.athena.cassandra.CassandraMatchSum
import ai.legends.athena.sum.MatchSumRow
import ai.legends.athena.sum.Permuter

object Main {
  def main(args: Array[String]) {
    val conf = new SparkConf(true).set("spark.cassandra.connection.host", "127.0.0.1")
    val sc = new SparkContext(conf)
    val rdd = sc.cassandraTable[CassandraMatch]("athena", "matches")

    // Ranks and match objects
    val matches = rdd.map(x => (x.toMatch(), x.rank))

    // Permutations of match sum rows
    val permutations = Permuter.groupPermutations(Permuter.permuteMatches(matches))

    // Convert to Cassandra equivalent
    val matchSums = permutations.map(matchSumRow => CassandraMatchSum(matchSumRow))
    matchSums.saveToCassandra("athena", "match_sums", AllColumns)

    println("Wrote " + matchSums.count() + " match sums.")
  }
}
