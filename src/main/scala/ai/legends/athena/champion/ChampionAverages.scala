package ai.legends.athena.champions

import ai.legends.athena.Match
import com.datastax.spark.connector._
import com.datastax.spark.connector.rdd.CassandraTableScanRDD

case class ChampionAverages(
  minionsKilled: Int,
  damageTaken: Int,
  damageDealt: Int,
  assists: Int,
  deaths: Int,
  kills: Int,
  goldEarned: Int,
  gamesPlayed: Int,
  banRate: Int,
  playRate: Int,
  winRate: Int
)

object ChampionAverages {

  def calculate(rdd: CassandraTableScanRDD[Match]): ChampionAverages = {
    val bans = rdd.flatMap(_.teams).flatMap(_.bans)

    return ChampionAverages(
      0,
      0,
      0,
      0,
      0,
      0,
      0,
      0,
      0,
      0,
      0
    )
  }

}
