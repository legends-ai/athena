package ai.legends.athena.matches

import org.json4s._
import org.json4s.JsonDSL._
import org.json4s.jackson.JsonMethods._

import org.scalatest._

class MatchSpec extends FlatSpec with Matchers {

  it should "transform participants into a JArray" in {
    val result = Match.transformParticipants(MatchFixtures.participantsJSON)
    result.arr.length should be(2)
    (result(0) \ "stats" \ "winner")should be(JBool(false))
    (result(1) \ "stats" \ "winner")should be(JBool(true))
  }

}
