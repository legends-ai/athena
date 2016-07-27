package ai.legends.athena.champions

import ai.legends.athena.matches.Participant

case class ChampionTotals(
  plays: Int = 0,
  playsByPlayer: Map[Int, Int] = Map[Int, Int](),
  wins: Int = 0,
  kills: Long = 0,
  deaths: Long = 0,
  assists: Long = 0
) {

  def add(participant: Participant): ChampionTotals = {
    ChampionTotals(
      plays = plays + 1,
      playsByPlayer = playsByPlayer + (participant.participantId ->
        (playsByPlayer.getOrElse(participant.participantId, 0) + 1)),
      wins = wins + (if (participant.stats.winner) 1 else 0),
      kills = kills + participant.stats.kda.kills,
      deaths = deaths + participant.stats.kda.deaths,
      assists = assists + participant.stats.kda.assists
    )
  }

  def +(other: ChampionTotals): ChampionTotals = {
    ChampionTotals(
      plays = plays + other.plays,
      playsByPlayer = playsByPlayer.foldLeft(other.playsByPlayer) {
        case (dict, (k, v)) => dict + (k -> (v + dict.getOrElse(k, 0)))
      },
      wins = wins + other.wins,
      kills = kills + other.kills,
      deaths = deaths + other.deaths,
      assists = assists + other.assists
    )
  }

}

object ChampionTotals {

  def apply(participant: Participant): ChampionTotals = {
    ChampionTotals().add(participant)
  }

}
