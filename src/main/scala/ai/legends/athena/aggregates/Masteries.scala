package ai.legends.athena.aggregates

import ai.legends.athena.matches.Mastery
import ai.legends.athena.utils.Combiners._
import scala.collection.immutable.Map

case class MasteriesAggregate(
  masteries: Map[MasterySet, Int] = Map()
) {

  def +(ms: MasterySet): MasteriesAggregate = {
    MasteriesAggregate(
      masteries ++ ms
    )
  }

  def +(ms: MasteriesAggregate): MasteriesAggregate = {
    MasteriesAggregate(
      masteries |+| ms.masteries
    )
  }

}

case class MasterySet(
  counts: Map[Int, Int]
)

object MasterySet {
  def fromList(masteries: List[Mastery]): MasterySet = {
    MasterySet(
      // identity is needed to allow Map to be serializable
      // http://stackoverflow.com/questions/32900862/map-can-not-be-serializable-in-scala
      masteries.groupBy(_.masteryId).mapValues(_.length).map(identity)
    )
  }
}
