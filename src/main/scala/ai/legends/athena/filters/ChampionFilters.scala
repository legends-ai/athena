package ai.legends.athena.filters

import ai.legends.athena.aggregates.ParticipantAggregate
import ai.legends.athena.matches.Match
import ai.legends.athena.matches.Participant
import org.apache.spark.rdd.RDD
import org.apache.spark.SparkContext._

case class ChampionFilters(
  m: MatchFilters,
  participant: ParticipantFilters
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
      case (key, agg) => (key.championFilters(agg.plays), agg)
    }.reduceByKey((v1, v2) => v1)
  }

}
