package ai.legends.athena.aggregates

import ai.legends.athena.utils.Combiners._

case class SkillsAggregate(
  skills: Map[SkillsSet, Int] = Map()
) {

  def +(ss: SkillsSet): SkillsAggregate = {
    SkillsAggregate(
      skills ++ ss
    )
  }

  def +(ss: SkillsAggregate): SkillsAggregate = {
    SkillsAggregate(
      skills |+| ss.skills
    )
  }

}

class SkillsSet(
  val first: Int,
  val second: Int
)

object SkillsSet {

  def apply(first: Int, second: Int): SkillsSet = {
    val min = Math.min(first, second)
    val max = Math.max(first, second)
    new SkillsSet(min, max)
  }

}
