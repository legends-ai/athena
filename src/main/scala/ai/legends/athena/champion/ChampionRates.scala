package ai.legends.athena.champions

case class ChampionRates(
  winRate: Double,
  banRate: Double,
  playRate: Double
)

object ChampionRates {
  def fromTotals(count: Long, totals: ChampionTotals): ChampionRates = {
    ChampionRates(
      totals.wins.toDouble / totals.plays,
      0.0,
      totals.plays.toDouble / count
    )
  }
}
