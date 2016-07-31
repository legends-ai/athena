package ai.legends.athena.aggregates

import ai.legends.athena.utils.Combiners._
import ai.legends.athena.matches.Rune
import scala.collection.immutable.Map

case class RunesAggregate(
  runes: Map[RuneSet, Int] = Map()
) {

  def +(rs: RuneSet): RunesAggregate = {
    RunesAggregate(
      runes ++ rs
    )
  }

  def +(rs: RunesAggregate): RunesAggregate = {
    RunesAggregate(
      runes |+| rs.runes
    )
  }

}

case class RuneSet(
  counts: Map[Int, Int]
)

object RuneSet {
  def fromList(runes: List[Rune]): RuneSet = {
    RuneSet(
      runes.groupBy(_.runeId).mapValues(_.length)
    )
  }
}
