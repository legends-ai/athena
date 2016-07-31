package ai.legends.athena.filters

import ai.legends.athena.aggregates.ParticipantAggregate

case class ParticipantFilters(
  championId: Int,
  gamesPlayed: Int
)
