package ai.legends.athena.champions

import ai.legends.athena.matches.Participant


case class ChampionTotals(
  plays: Int,
  playsByPlayer: Map[Int, Int]
) {

  def add(participant: Participant): ChampionTotals = {
    val nextPlayerPlays = playsByPlayer.getOrElse(participant.participantId, 0) + 1
    ChampionTotals(
      plays + 1,
      playsByPlayer + (participant.participantId -> nextPlayerPlays)
    )
  }

  def +(other: ChampionTotals): ChampionTotals = {
    ChampionTotals(
      plays + other.plays,
      playsByPlayer.foldLeft(other.playsByPlayer) {
        case (dict, (k, v)) => dict + (k -> (v + dict.getOrElse(k, 0)))
      }
    )
  }

}

object ChampionTotals {

  def apply(): ChampionTotals = {
    ChampionTotals(
      0,
      Map[Int, Int]()
    )
  }

  def apply(participant: Participant): ChampionTotals = {
    ChampionTotals().add(participant)
  }
}
