package ai.legends.athena.matches

import org.json4s._
import org.json4s.JsonDSL._
import org.json4s.jackson.JsonMethods._

import org.scalatest._

class ParticipantStatsSpec extends FlatSpec with Matchers {

  it should "extract relevant fields into separate objects" in {
    implicit val formats = DefaultFormats
    val result = ParticipantStats.jsonFromParticipants(MatchFixtures.statsJSON).extract[ParticipantStats]
    result should be(MatchFixtures.stats)
  }

}
