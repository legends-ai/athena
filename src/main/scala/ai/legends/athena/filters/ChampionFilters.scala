package ai.legends.athena.filters

import ai.legends.athena.aggregates.ParticipantAggregate
import ai.legends.athena.matches.Match
import ai.legends.athena.matches.Participant
import org.apache.spark.SparkContext._
import org.apache.spark.rdd.RDD

case class ChampionFilters(
  championId: Int,
  gamesPlayed: Range,
  patch: String,
  duration: Range
)

object ChampionFilters {

  /**
    * Group all of the participants together by filters
    */
  def groupRDD(rdd: RDD[(Match, Participant)]): RDD[(ChampionFilters, ParticipantAggregate)] = {
    rdd.map {
      case (m, p) =>
        (ParticipantGroupKey(m, p), p)
    }.aggregateByKey(ParticipantAggregate())(
      _ + _,
      _ + _
    ).map {
      case (key, agg) => (key.filter(agg.plays), agg)
    }.reduceByKey((v1, v2) => v1)
  }

}
