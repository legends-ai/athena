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

}
