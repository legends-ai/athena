package ai.legends.athena.champions

import ai.legends.athena.filters.ChampionFilters
import ai.legends.athena.matches._
import ai.legends.athena.aggregates._
import com.datastax.spark.connector._
import org.apache.spark.SparkContext
import org.apache.spark.SparkContext._
import com.datastax.spark.connector.rdd.CassandraTableScanRDD
import org.apache.spark.rdd.RDD

case class ChampionReport(
  id: Int,
  scalars: ChampionScalars
)

object ChampionReport {

  def calculateAll(rdd: RDD[Match]): Set[(ChampionFilters, ParticipantAggregate)] = {
    val count = rdd.count
    val aggs = ChampionFilters.buildAggregates(rdd).collect().toSet
    return aggs
  }

  def bansByChamp(rdd: RDD[Ban])  = {
    rdd.map(x => (x.championId, 1)).reduceByKey(_ + _).collectAsMap()
  }

}

case class ChampionData(
  id: Int,
  count: Long,
  bans: Int,
  participantAgg: ParticipantAggregate
)
