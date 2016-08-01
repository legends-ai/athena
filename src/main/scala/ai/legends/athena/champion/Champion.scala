package ai.legends.athena.champions

import ai.legends.athena.filters.ChampionFilters
import ai.legends.athena.matches._
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
    val filteredAggs = calculateParticipants(rdd)

    filteredAggs.map {
      case (filters, agg) => (filters.participant.championId, agg)
    }.reduceByKey((v1, v2) => v1).map {
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
    }.collect().toSet
  }

  def calculateParticipants(rdd: RDD[Match]): RDD[(ChampionFilters, ParticipantAggregate)] = {
    ChampionFilters.groupRDD(rdd.flatMap(m => m.participants.map(p => (m, p))))
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
