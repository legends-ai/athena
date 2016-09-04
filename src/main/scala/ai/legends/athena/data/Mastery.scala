package ai.legends.athena.data

case class Mastery (
  masteryId: Int,
  rank: Int
)

object Mastery {

  def listToString(masteries: List[Mastery]): String = {
    masteries.groupBy(identity).mapValues(_.size).map {
      case (mastery, amount) => s"${mastery.masteryId}:${mastery.rank}:${amount}"
    }.toSeq.sorted.mkString("|")
  }

  val keystones = List[Int](
    // TODO(pradyuman): keystone mastery ids
  )

  def listToKeystoneString(masteries: List[Mastery]): String = {
    listToString(masteries.filter(m => keystones.contains(m.masteryId)))
  }

}
