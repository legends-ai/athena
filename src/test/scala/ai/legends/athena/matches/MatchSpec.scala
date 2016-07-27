package ai.legends.athena.matches

import org.json4s._
import org.json4s.JsonDSL._
import org.json4s.jackson.JsonMethods._

import org.scalatest._

class MatchSpec extends FlatSpec with Matchers {

  it should "adapt JSON into a JValue" in {
    val result = Match.adaptJSON(MatchFixtures.matchJSON)
    (result \ "asdf") should be(JInt(123))
    ((result \ "participants")(0) \ "stats" \ "winner") should be(JBool(false))
    ((result \ "participants")(1) \ "stats" \ "winner") should be(JBool(true))
  }

  it should "transform participants into a JArray" in {
    val result = Match.transformParticipants(MatchFixtures.participantsJSON)
    result.arr.length should be(2)
    (result(0) \ "stats" \ "winner")should be(JBool(false))
    (result(1) \ "stats" \ "winner")should be(JBool(true))
  }

}
