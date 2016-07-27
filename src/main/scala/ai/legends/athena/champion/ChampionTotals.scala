package ai.legends.athena.champions

import ai.legends.athena.matches.Participant


case class ChampionTotals(
  plays: Int,
  playsByPlayer: Map[Int, Int],
  wins: Int
) {

  def add(participant: Participant): ChampionTotals = {
    ChampionTotals(
      plays + 1,
      playsByPlayer + (participant.participantId ->
        (playsByPlayer.getOrElse(participant.participantId, 0) + 1)),
      wins + (if (participant.stats.winner) 1 else 0)
    )
  }

  def +(other: ChampionTotals): ChampionTotals = {
    ChampionTotals(
      plays + other.plays,
      playsByPlayer.foldLeft(other.playsByPlayer) {
        case (dict, (k, v)) => dict + (k -> (v + dict.getOrElse(k, 0)))
      },
      wins + other.wins
    )
  }

}

object ChampionTotals {

  def apply(): ChampionTotals = {
    ChampionTotals(
      0,
      Map[Int, Int](),
      0
    )
  }

  def apply(participant: Participant): ChampionTotals = {
    ChampionTotals().add(participant)
  }
}
