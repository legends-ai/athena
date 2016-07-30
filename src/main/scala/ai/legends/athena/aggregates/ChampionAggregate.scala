package ai.legends.athena.aggregates

case class ChampionAggregate(
  bans: Long = 0,
  participant: ParticipantAggregate = ParticipantAggregate()
)
