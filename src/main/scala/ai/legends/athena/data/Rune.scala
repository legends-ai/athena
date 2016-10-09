package io.asuna.asunasan.legends.riotmatch

case class Rune (
  rank: Int,
  runeId: Int
)

object Rune {

  def listToString(runes: List[Rune]): String = {
    runes.groupBy(identity).mapValues(_.size).map {
      case (rune, amount) => s"${rune.runeId}:${rune.rank}:${rune.rank}"
    }.toSeq.sorted.mkString("|")
  }

}
