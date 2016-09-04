package ai.legends.athena

import org.apache.spark.{ SparkConf, SparkContext }
import com.datastax.spark.connector._
import ai.legends.athena.sum.MatchSumRow
import ai.legends.athena.sum.Permuter

import org.json4s._
import org.json4s.jackson.JsonMethods._
import org.json4s.jackson.Serialization
import org.json4s.jackson.Serialization.{read, write}

object Main {
  def main(args: Array[String]) {
    val conf = new SparkConf(true).set("spark.cassandra.connection.host", "127.0.0.1")
    val sc = new SparkContext(conf)
    val rdd = sc.cassandraTable[CassandraMatch]("athena", "matches")

    // Ranks and match objects
    val matches = rdd.map(x => (x.toMatch(), x.rank))

    // Permutations of match sum rows
    val permutations = Permuter.permuteMatches(Permuter.buildMatchSumRows(matches))

    // implicit val formats = Serialization.formats(NoTypeHints)
    // println(write(champs.head))
    println(permutations.count())
  }
}
