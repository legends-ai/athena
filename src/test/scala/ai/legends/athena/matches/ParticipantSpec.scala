package ai.legends.athena.matches

import org.json4s._
import org.json4s.JsonDSL._
import org.json4s.jackson.JsonMethods._

import org.scalatest._

class ParticipantSpec extends FlatSpec with Matchers {

  it should "transform a stats subobject" in {
    implicit val formats = DefaultFormats
    val result = Participant.transform(MatchFixtures.participantJSON).extract[Participant]
    result should be(MatchFixtures.participant)
  }

}
