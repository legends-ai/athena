package ai.legends.athena.aggregates

import ai.legends.athena.matches.Mastery
import ai.legends.athena.utils.Combiners._
import ai.legends.athena.matches.Rune
import scala.collection.immutable.Map

case class MasteriesAggregate(
  runes: Map[MasterySet, Int] = Map()
) {

  def +(rs: MasterySet): MasteriesAggregate = {
    MasteriesAggregate(
      runes ++ rs
    )
  }

  def +(rs: MasteriesAggregate): MasteriesAggregate = {
    MasteriesAggregate(
      runes |+| rs.runes
    )
  }

}

case class MasterySet(
  counts: Map[Int, Int]
)

object MasterySet {
  def fromList(masteries: List[Mastery]): MasterySet = {
    MasterySet(
      masteries.groupBy(_.masteryId).mapValues(_.length)
    )
  }
}
