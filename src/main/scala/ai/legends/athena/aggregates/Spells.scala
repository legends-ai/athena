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

case class SpellsSet(
  first: Int,
  second: Int
)

object SpellsSet {

  def create(first: Int, second: Int): SpellsSet = {
    SpellsSet(Math.min(first, second), Math.max(first, second))
  }

}
