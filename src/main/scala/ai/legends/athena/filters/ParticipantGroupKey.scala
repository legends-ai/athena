package ai.legends.athena.filters

import ai.legends.athena.matches.Match
import ai.legends.athena.matches.Participant

/**
  *  ParticipantGroupKey is derived from match and participant to create filters.
  */
case class ParticipantGroupKey(
  patch: String,
  duration: Int,
  championId: Int,
  participantId: Int
) {

  def championFilters(plays: Int) = ChampionFilters(
    matchFilters,
    participantFilters(plays)
  )

  def matchFilters = MatchFilters(patch, duration)

  def participantFilters(plays: Int) = ParticipantFilters(championId, plays)

}

object ParticipantGroupKey {

  def apply(m: Match, participant: Participant): ParticipantGroupKey = {
    ParticipantGroupKey(
      m.matchVersion,
      m.matchDuration,
      participant.championId,
      participant.participantId
    )
  }

}
