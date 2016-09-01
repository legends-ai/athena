package ai.legends.athena.filters

import ai.legends.athena.aggregates.ParticipantAggregate
import ai.legends.athena.matches.Match
import ai.legends.athena.matches.Participant
import org.apache.spark.SparkContext._
import org.apache.spark.rdd.RDD

case class ChampionFilters(
  championId: Option[Int] = None,
  gamesPlayed: Option[Range] = None,
  patch: Option[String] = None,
  duration: Option[Range] = None,
  tier: Option[Int] = None,
  region: Option[String] = None,
  role: Option[String] = None
) {

  def matchFilter = {
    MatchFilters(
      patch,
      duration,
      tier,
      region
    )
  }

}

object ChampionFilters {

  def buildAggregates(rdd: RDD[Match]): RDD[(ChampionFilters, ParticipantAggregate)] = {
    // Find pairs
    val pairs = rdd.flatMap(m => m.participants.map(p => (m, p)))

    // Aggregate rdd by group key
    val aggregates = pairs.map {
      case (m, p) =>
        (ParticipantGroupKey(m, p), p)
    }.aggregateByKey(ParticipantAggregate())(_ + _, _ + _)

    // Filter aggregates
    val filtered = aggregates.map {
      case (key, agg) => (key.filter(agg.plays), agg)
    }.reduceByKey(_ + _)

    // Calculate filter permutations
    val matchupFiltered = filtered.map {
      case (filters, agg) =>
        (filters.copy(
          gamesPlayed = None,
          patch = None,
          duration = None
        ), agg)
    }.reduceByKey(_ + _)

    return matchupFiltered

    val noTier = matchupFiltered.map {
      case (filters, agg) =>
        (filters.copy(
          tier = None
        ), agg)
    }.reduceByKey(_ + _)
    val noRegion = matchupFiltered.map {
      case (filters, agg) =>
        (filters.copy(
          region = None
        ), agg)
    }.reduceByKey(_ + _)
    val noRole = matchupFiltered.map {
      case (filters, agg) =>
        (filters.copy(
          role = None
        ), agg)
    }.reduceByKey(_ + _)
    val noTierOrRegion = noTier.map {
      case (filters, agg) =>
        (filters.copy(
          region = None
        ), agg)
    }.reduceByKey(_ + _)
    val noTierOrRole = noTier.map {
      case (filters, agg) =>
        (filters.copy(
          role = None
        ), agg)
    }.reduceByKey(_ + _)
    val noRegionOrRole = noRegion.map {
      case (filters, agg) =>
        (filters.copy(
          role = None
        ), agg)
    }.reduceByKey(_ + _)
    val noFilter = noTierOrRegion.map {
      case (filters, agg) =>
        (filters.copy(
          role = None
        ), agg)
    }.reduceByKey(_ + _)

    // Union all rdds
    List(
      matchupFiltered,
      noTier,
      noRegion,
      noRole,
      noTierOrRegion,
      noTierOrRole,
      noRegionOrRole,
      noFilter
    ).reduce(_ union _)
  }

}
