package ai.legends.athena.derivations

import ai.legends.athena.aggregates.StatsAggregate

case class StatsDerivation (
  winRate: Double = 0
  // combatPlayerScore: Long = 0,
  // goldEarned: Long = 0,
  // goldSpent: Long = 0,
  // objectivePlayerScore: Long = 0,
  // totalHeal: Long = 0,
  // totalPlayerScore: Long = 0,
  // totalScoreRank: Long = 0,
  // totalTimeCrowdControlDealt: Long = 0,
  // totalUnitsHealed: Long = 0,
  // damage: DamageAggregate = DamageAggregate(),
  // firsts: FirstsAggregate = FirstsAggregate(),
  // items: ItemsAggregate = ItemsAggregate(),
  // kda: KDAAggregate = KDAAggregate(),
  // wards: WardsAggregate = WardsAggregate()
)

object StatsDerivation {

  def apply(stats: StatsAggregate): StatsDerivation = {
    StatsDerivation(
      winRate = stats.winner.toDouble / stats.plays
    )
  }

}
