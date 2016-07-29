package ai.legends.athena.aggregates

import ai.legends.athena.matches.Participant
import ai.legends.athena.utils.Combiners._

case class ChampionAggregate(
  plays: Int = 0,
  playsByPlayer: Map[Int, Int] = Map[Int, Int](),
  stats: StatsAggregate = StatsAggregate()
) {

  def +(other: Participant): ChampionAggregate = {
    ChampionAggregate(
      plays + 1,
      playsByPlayer ++ other.participantId,
      stats + other.stats
    )
  }

  def +(other: ChampionAggregate): ChampionAggregate = {
    ChampionAggregate(
      plays + other.plays,
      playsByPlayer |+| other.playsByPlayer,
      stats + other.stats
    )
  }

}

object ChampionAggregate {

  def apply(participant: Participant): ChampionAggregate = {
    ChampionAggregate() + participant
  }

}
