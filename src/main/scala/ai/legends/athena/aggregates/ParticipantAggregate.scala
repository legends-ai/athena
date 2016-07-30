package ai.legends.athena.aggregates

import ai.legends.athena.matches.Participant
import ai.legends.athena.utils.Combiners._
import org.apache.spark.rdd.RDD
import org.apache.spark.SparkContext._

case class ParticipantAggregate(
  plays: Int = 0,
  playsByPlayer: Map[Int, Int] = Map[Int, Int](),
  stats: StatsAggregate = StatsAggregate()
) {

  def +(other: Participant): ParticipantAggregate = {
    ParticipantAggregate(
      plays + 1,
      playsByPlayer ++ other.participantId,
      stats + other.stats
    )
  }

  def +(other: ParticipantAggregate): ParticipantAggregate = {
    ParticipantAggregate(
      plays + other.plays,
      playsByPlayer |+| other.playsByPlayer,
      stats + other.stats
    )
  }

}

object ParticipantAggregate {

  def apply(participant: Participant): ParticipantAggregate = {
    ParticipantAggregate() + participant
  }

  def fromRDD(rdd: RDD[Participant]): RDD[(Int, ParticipantAggregate)] = {
    rdd
      .map(p => (p.championId, p))
      .combineByKey[ParticipantAggregate](
      ParticipantAggregate(_: Participant),
        (acc: ParticipantAggregate, p: Participant) => acc + p,
        (a: ParticipantAggregate, b: ParticipantAggregate) => a + b
    )
  }

}
