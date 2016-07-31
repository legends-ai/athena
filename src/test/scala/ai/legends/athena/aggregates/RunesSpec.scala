package ai.legends.athena.aggregates

import ai.legends.athena.matches.Rune
import org.scalatest._

class RunesSpec extends FlatSpec with Matchers {

  it should "create a runeset from rune list" in {
    val runes = Rune(0, 10) ::
      Rune(0, 10) ::
      Rune(0, 10) ::
      Rune(0, 10) ::
      Rune(0, 10) ::
      Rune(0, 20) ::
      Rune(0, 20) ::
      Rune(0, 20) :: Nil
    val expected = RuneSet(Map[Int, Int](10 -> 5, 20 -> 3))
    val result = RuneSet.fromList(runes)
    result should be(expected)
  }

  it should "allow unique rune sets to be counted as the same" in {
    val a = RuneSet(Map[Int, Int](10 -> 4, 84 -> 2))
    val b = RuneSet(Map[Int, Int](10 -> 4, 84 -> 2))
    val expected = RunesAggregate(
      Map[RuneSet, Int](
        RuneSet(Map[Int, Int](10 -> 4, 84 -> 2)) -> 2
      )
    )
    val result = RunesAggregate() + a + b
    result should be(expected)
  }

}
