package ai.legends.athena.champions

import ai.legends.athena.matches.Match
import com.datastax.spark.connector._
import org.apache.spark.SparkContext._
import com.datastax.spark.connector.rdd.CassandraTableScanRDD
import org.apache.spark.rdd.RDD

case class Champion(
  bans: Int,
  plays: Int,
  banRate: Double,
  playRate: Double
)

object Champion {

  def calculateAll(rdd: RDD[Match]): Set[Champion] = {
    val bans = rdd.flatMap(_.teams).flatMap(_.bans)
      .map(x => (x.championId, 1)).reduceByKey(_ + _).collectAsMap()
    val plays = rdd.flatMap(_.participants)
      .map(x => (x.championId, 1)).reduceByKey(_ + _).collectAsMap()
    val wins = rdd.flatMap(_.participants).filter(_.stats.winner)
      .map(x => (x.championId, 1)).reduceByKey(_ + _).collectAsMap()

    println(bans)
    println(plays)
    println(wins)
    return null
  }

}
