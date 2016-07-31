package ai.legends.athena.champions

import ai.legends.athena.matches.{ Ban, Match, Participant }
import ai.legends.athena.aggregates._
import com.datastax.spark.connector._
import org.apache.spark.SparkContext
import org.apache.spark.SparkContext._
import com.datastax.spark.connector.rdd.CassandraTableScanRDD
import org.apache.spark.rdd.RDD

case class Champion(
  id: Int,
  scalars: ChampionScalars
)

object Champion {

  def calculateAll(rdd: RDD[Match]): Set[Champion] = {
    val count = rdd.count

    val participants = rdd.flatMap(_.participants)
    val bansMap = bansByChamp(rdd.flatMap(_.teams).flatMap(_.bans))
    val participantAggs = ParticipantAggregate.fromRDD(participants).collectAsMap()

    participantAggs.map {
      case (id, participantAgg) =>
        ChampionData(
          id,
          count,
          bansMap.getOrElse(id, 0),
          participantAgg
        )
    }.map {
      case data =>
        Champion(
          data.id,
          ChampionScalars(data)
        )
    }.toSet
  }

  def bansByChamp(rdd: RDD[Ban]) = {
    rdd.map(x => (x.championId, 1)).reduceByKey(_ + _).collectAsMap()
  }

}

case class ChampionData(
  id: Int,
  count: Long,
  bans: Int,
  participantAgg: ParticipantAggregate
)
