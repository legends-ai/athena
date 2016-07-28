package ai.legends.athena.aggregates

import ai.legends.athena.utils.Combiners._

case class ChampionItems (
  item0: Map[Int, Int] = Map(),
  item1: Map[Int, Int] = Map(),
  item2: Map[Int, Int] = Map(),
  item3: Map[Int, Int] = Map(),
  item4: Map[Int, Int] = Map(),
  item5: Map[Int, Int] = Map(),
  item6: Map[Int, Int] = Map()
) {

  def +(items: ChampionItems): ChampionItems = {
    ChampionItems(
      item0 |+| items.item0,
      item1 |+| items.item1,
      item2 |+| items.item2,
      item3 |+| items.item3,
      item4 |+| items.item4,
      item5 |+| items.item5,
      item6 |+| items.item6
    )
  }

  def +(items: ParticipantItems): ChampionItems = {
    ChampionItems(
      item0 ++ items.item0,
      item1 ++ items.item1,
      item2 ++ items.item2,
      item3 ++ items.item3,
      item4 ++ items.item4,
      item5 ++ items.item5,
      item6 ++ items.item6
    )
  }

}

case class ParticipantItems (
  item0: Int,
  item1: Int,
  item2: Int,
  item3: Int,
  item4: Int,
  item5: Int,
  item6: Int
)

object ParticipantItems {
  val fields = classOf[ParticipantItems].getDeclaredFields.map(_.getName).toList
}
