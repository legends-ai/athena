package ai.legends.athena.champions

case class ChampionRates(
  winRate: Double,
  banRate: Double,
  playRate: Double
)

object ChampionRates {
  def fromTotals(count: Long, bans: Int, totals: ChampionTotals): ChampionRates = {
    ChampionRates(
      winRate = totals.wins.toDouble / totals.plays,
      banRate = bans.toDouble / totals.plays,
      playRate = totals.plays.toDouble / count
    )
  }
}
