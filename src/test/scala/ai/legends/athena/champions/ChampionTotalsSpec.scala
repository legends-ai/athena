package ai.legends.athena.champions

import org.scalatest._

class ChampionTotalsSpec extends FlatSpec with Matchers {

  it should "merge plays properly" in {
    val total1 = ChampionTotals(
      25,
      Map(1 -> 3, 2 -> 4),
      4,
      1,
      2,
      3
    )
    val total2 = ChampionTotals(
      13,
      Map(1 -> 2, 3 -> 5),
      2,
      4,
      5,
      6
    )
    val result = total1 + total2
    result should be(ChampionTotals(
      38,
      Map(1 -> 5, 2 -> 4, 3 -> 5),
      6,
      5,
      7,
      9
    ))
  }

}
