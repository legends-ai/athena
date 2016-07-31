package ai.legends.athena.aggregates

import ai.legends.athena.matches.Rune
import scala.collection.immutable.Map

case class RunesAggregate(
  runes: Map[RuneSet, Int] = Map()
)

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
