package ai.legends.athena.champions

import ai.legends.athena.matches.{ Match, Participant }
import com.datastax.spark.connector._
import org.apache.spark.SparkContext
import org.apache.spark.SparkContext._
import com.datastax.spark.connector.rdd.CassandraTableScanRDD
import org.apache.spark.rdd.RDD

case class Champion(
  id: Int,
  bans: Int,
  plays: Int,
  banRate: Double,
  playRate: Double
)

object Champion {

  def calculateAll(rdd: RDD[Match])(implicit sc: SparkContext): Set[Champion] = {
    val count = rdd.count
    val bans = rdd.flatMap(_.teams).flatMap(_.bans)
      .map(x => (x.championId, 1)).reduceByKey(_ + _).collectAsMap()
    val participants = rdd.flatMap(_.participants)

    val totals = participants
      .map(p => (p.championId, p))
      .combineByKey[ChampionTotals](
        ChampionTotals(_: Participant),
        (acc: ChampionTotals, p: Participant) => acc.add(p),
        (a: ChampionTotals, b: ChampionTotals) => a + b
      )

    println(totals)
    return null
  }

  def calculate(id: Int, rdd: RDD[Participant]): Champion = {
    null
  }

}
