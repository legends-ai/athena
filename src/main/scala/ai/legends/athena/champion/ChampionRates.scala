package ai.legends.athena.champions

import ai.legends.athena.aggregates._

case class ChampionRates(
  winRate: Double,
  banRate: Double,
  playRate: Double
)

object ChampionRates {
  def fromTotals(count: Long, bans: Int, agg: ChampionAggregate): ChampionRates = {
    ChampionRates(
      winRate = agg.stats.winner.toDouble / agg.stats.plays,
      banRate = bans.toDouble / agg.stats.plays,
      playRate = agg.stats.plays.toDouble / count
    )
  }
}
