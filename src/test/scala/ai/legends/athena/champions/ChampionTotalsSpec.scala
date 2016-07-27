package ai.legends.athena.champions

import org.scalatest._

class ChampionTotalsSpec extends FlatSpec with Matchers {

  it should "merge plays properly" in {
    val total1 = ChampionTotals(
      plays =  25,
      playsByPlayer = Map(1 -> 3, 2 -> 4),
      wins = 4,
      kills = 1,
      deaths = 2,
      assists = 3
    )
    val total2 = ChampionTotals(
      plays = 13,
      playsByPlayer = Map(1 -> 2, 3 -> 5),
      wins = 2,
      kills = 4,
      deaths = 5,
      assists = 6
    )
    val result = total1 + total2
    result should be(ChampionTotals(
      plays = 38,
      playsByPlayer = Map(1 -> 5, 2 -> 4, 3 -> 5),
      wins = 6,
      kills = 5,
      deaths = 7,
      assists = 9
    ))
  }

}
