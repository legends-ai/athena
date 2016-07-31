package ai.legends.athena.aggregates

import ai.legends.athena.utils.Combiners._

case class SpellsAggregate(
  spells: Map[SpellsSet, Int] = Map()
) {

  def +(ss: SpellsSet): SpellsAggregate = {
    SpellsAggregate(
      spells ++ ss
    )
  }

  def +(ss: SpellsAggregate): SpellsAggregate = {
    SpellsAggregate(
      spells |+| ss.spells
    )
  }

}

class SpellsSet(
  val first: Int,
  val second: Int
)

object SpellsSet {

  def apply(first: Int, second: Int): SpellsSet = {
    val min = Math.min(first, second)
    val max = Math.max(first, second)
    new SpellsSet(min, max)
  }

}
