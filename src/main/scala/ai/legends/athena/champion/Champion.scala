package ai.legends.athena.champions

import ai.legends.athena.matches.{ Match, Participant }
import ai.legends.athena.aggregates._
import com.datastax.spark.connector._
import org.apache.spark.SparkContext
import org.apache.spark.SparkContext._
import com.datastax.spark.connector.rdd.CassandraTableScanRDD
import org.apache.spark.rdd.RDD

case class Champion(
  id: Int,
  banCt: Int,
  participantAgg: ParticipantAggregate
)

object Champion {

  def calculateAll(rdd: RDD[Match])(implicit sc: SparkContext): Set[Champion] = {
    val count = rdd.count
    val participants = rdd.flatMap(_.participants)

    val bans = rdd.flatMap(_.teams).flatMap(_.bans)
    val bansByChamp = bans.map(x => (x.championId, 1)).reduceByKey(_ + _).collectAsMap()

    val participantAggs = ParticipantAggregate.fromRDD(participants)

    val champs = participantAggs.map {
      case (id, participantAgg) => {
        val banCt = bansByChamp.getOrElse(id, 0)
        Champion(
          id = id,
          banCt = banCt,
          participantAgg = participantAgg
        )
      }
    }

    champs.collect().toSet
  }

}
