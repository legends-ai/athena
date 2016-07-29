package ai.legends.athena.aggregates

case class DamageAggregate (
  magicDamageDealt: Long = 0,
  magicDamageDealtToChampions: Long = 0,
  magicDamageTaken: Long = 0,
  physicalDamageDealt: Long = 0,
  physicalDamageDealtToChampions: Long = 0,
  physicalDamageTaken: Long = 0,
  totalDamageDealt: Long = 0,
  totalDamageDealtToChampions: Long = 0,
  trueDamageDealt: Long = 0,
  trueDamageDealtToChampions: Long = 0,
  trueDamageTaken: Long = 0,
  largestCriticalStrike: Long = 0,
  totalDamageTaken: Long = 0
) {

  def +(dmg: ParticipantDamage): DamageAggregate = {
    DamageAggregate(
      magicDamageDealt + dmg.magicDamageDealt,
      magicDamageDealtToChampions + dmg.magicDamageDealtToChampions,
      magicDamageTaken + dmg.magicDamageTaken,
      physicalDamageDealt + dmg.physicalDamageDealt,
      physicalDamageDealtToChampions + dmg.physicalDamageDealtToChampions,
      physicalDamageTaken + dmg.physicalDamageTaken,
      totalDamageDealt + dmg.totalDamageDealt,
      totalDamageDealtToChampions + dmg.totalDamageDealtToChampions,
      trueDamageDealt + dmg.trueDamageDealt,
      trueDamageDealtToChampions + dmg.trueDamageDealtToChampions,
      trueDamageTaken + dmg.trueDamageTaken,
      largestCriticalStrike + dmg.largestCriticalStrike,
      totalDamageTaken + dmg.totalDamageTaken
    )
  }

  def +(dmg: DamageAggregate): DamageAggregate = {
    DamageAggregate(
      magicDamageDealt + dmg.magicDamageDealt,
      magicDamageDealtToChampions + dmg.magicDamageDealtToChampions,
      magicDamageTaken + dmg.magicDamageTaken,
      physicalDamageDealt + dmg.physicalDamageDealt,
      physicalDamageDealtToChampions + dmg.physicalDamageDealtToChampions,
      physicalDamageTaken + dmg.physicalDamageTaken,
      totalDamageDealt + dmg.totalDamageDealt,
      totalDamageDealtToChampions + dmg.totalDamageDealtToChampions,
      trueDamageDealt + dmg.trueDamageDealt,
      trueDamageDealtToChampions + dmg.trueDamageDealtToChampions,
      trueDamageTaken + dmg.trueDamageTaken,
      largestCriticalStrike + dmg.largestCriticalStrike,
      totalDamageTaken + dmg.totalDamageTaken
    )
  }

}

object DamageAggregate {
}

case class ParticipantDamage (
  magicDamageDealt: Int,
  magicDamageDealtToChampions: Int,
  magicDamageTaken: Int,
  physicalDamageDealt: Int,
  physicalDamageDealtToChampions: Int,
  physicalDamageTaken: Int,
  totalDamageDealt: Int,
  totalDamageDealtToChampions: Int,
  trueDamageDealt: Int,
  trueDamageDealtToChampions: Int,
  trueDamageTaken: Int,
  largestCriticalStrike: Int,
  totalDamageTaken: Int
)
