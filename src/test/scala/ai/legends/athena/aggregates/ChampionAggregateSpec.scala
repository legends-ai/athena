package ai.legends.athena.aggregates

import org.scalatest._

class ChampionAggregateSpec extends FlatSpec with Matchers {

  it should "merge plays properly" in {
    val total1 = ChampionAggregate(
      playsByPlayer = Map(1 -> 3, 2 -> 4)
    )
    val total2 = ChampionAggregate(
      playsByPlayer = Map(1 -> 2, 3 -> 5)
    )
    val result = total1 + total2
    result should be(ChampionAggregate(
      playsByPlayer = Map(1 -> 5, 2 -> 4, 3 -> 5)
    ))
  }

}
