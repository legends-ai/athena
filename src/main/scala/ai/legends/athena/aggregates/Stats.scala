package ai.legends.athena.aggregates

case class StatsAggregate (
  plays: Long = 0,
  winner: Long = 0,
  champLevel: Long = 0,
  combatPlayerScore: Long = 0,
  goldEarned: Long = 0,
  goldSpent: Long = 0,
  objectivePlayerScore: Long = 0,
  totalHeal: Long = 0,
  totalPlayerScore: Long = 0,
  totalScoreRank: Long = 0,
  totalTimeCrowdControlDealt: Long = 0,
  totalUnitsHealed: Long = 0,
  damage: DamageAggregate = DamageAggregate(),
  firsts: FirstsAggregate = FirstsAggregate(),
  items: ItemsAggregate = ItemsAggregate(),
  kda: KDAAggregate = KDAAggregate(),
  wards: WardsAggregate = WardsAggregate()
) {

  def +(stats: ParticipantStats): StatsAggregate = {
    StatsAggregate(
      plays + 1,
      winner + (if (stats.winner) 1 else 0),
      champLevel + stats.champLevel,
      combatPlayerScore + stats.combatPlayerScore,
      goldEarned + stats.goldEarned,
      goldSpent + stats.goldSpent,
      objectivePlayerScore + stats.objectivePlayerScore,
      totalHeal + stats.totalHeal,
      totalPlayerScore + stats.totalPlayerScore,
      totalScoreRank + stats.totalScoreRank,
      totalTimeCrowdControlDealt + stats.totalTimeCrowdControlDealt,
      totalUnitsHealed + stats.totalUnitsHealed,
      damage + stats.damage,
      firsts + stats.firsts,
      items + stats.items,
      kda + stats.kda,
      wards + stats.wards
    )
  }

  def +(stats: StatsAggregate): StatsAggregate = {
    StatsAggregate(
      plays + stats.plays,
      winner + stats.winner,
      champLevel + stats.champLevel,
      combatPlayerScore + stats.combatPlayerScore,
      goldEarned + stats.goldEarned,
      goldSpent + stats.goldSpent,
      objectivePlayerScore + stats.objectivePlayerScore,
      totalHeal + stats.totalHeal,
      totalPlayerScore + stats.totalPlayerScore,
      totalScoreRank + stats.totalScoreRank,
      totalTimeCrowdControlDealt + stats.totalTimeCrowdControlDealt,
      totalUnitsHealed + stats.totalUnitsHealed,
      damage + stats.damage,
      firsts + stats.firsts,
      items + stats.items,
      kda + stats.kda,
      wards + stats.wards
    )
  }

}

case class ParticipantStats (
  winner: Boolean,
  champLevel: Int,
  combatPlayerScore: Int,
  goldEarned: Int,
  goldSpent: Int,
  objectivePlayerScore: Int,
  totalHeal: Int,
  totalPlayerScore: Int,
  totalScoreRank: Int,
  totalTimeCrowdControlDealt: Int,
  totalUnitsHealed: Int,
  damage: ParticipantDamage,
  firsts: ParticipantFirsts,
  items: ParticipantItems,
  kda: ParticipantKDA,
  wards: ParticipantWards
)
