package ai.legends.athena

import ai.legends.athena.champions.ChampionReport
import org.apache.spark.{ SparkConf, SparkContext }
import com.datastax.spark.connector._
import org.json4s._
import org.json4s.jackson.JsonMethods._
import org.json4s.jackson.Serialization
import org.json4s.jackson.Serialization.{read, write}

object Main {
  def main(args: Array[String]) {
    val conf = new SparkConf(true).set("spark.cassandra.connection.host", "127.0.0.1")
    val sc = new SparkContext(conf)
    val rdd = sc.cassandraTable[CassandraMatch]("athena", "matches")
    val matches = rdd.map(x => x.toMatch())
    val champs = ChampionReport.calculateAll(matches)
    implicit val formats = Serialization.formats(NoTypeHints)
    println(write(champs.head))
  }
}
