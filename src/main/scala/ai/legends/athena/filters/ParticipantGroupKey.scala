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

  def filter(plays: Int): ChampionFilters = {
    ChampionFilters(
      championId,
      gamesPlayedRange(plays),
      patch,
      durationRange(duration)
    )
  }

  def findRange(ranges: List[Range], n: Int): Range = {
    ranges.find(_.contains(n)).get
  }

  val gamesPlayedRanges =
    (0 until 5) ::
    (5 until 15) ::
    (15 until 50) ::
    (50 until 125) ::
    (125 until Int.MaxValue) :: Nil

  def gamesPlayedRange(n: Int): Range =
    findRange(gamesPlayedRanges, n)

  val durationRanges =
    (0 until 25 * 60) ::
    (25 * 60 until 30 * 60) ::
    (30 * 60 until 35 * 60) ::
    (35 * 60 until 40 * 60) ::
    (40 * 60 until Int.MaxValue) :: Nil

  def durationRange(n: Int): Range =
    findRange(durationRanges, n)

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
