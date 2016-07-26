package ai.legends.athena.matches

import org.json4s._
import org.json4s.JsonDSL._
import org.json4s.jackson.JsonMethods._

case class Participant (
  stats: ParticipantStats,
  highestAchievedSeasonTier: String,
  teamId: Int,
  spell1Id: Int,
  spell2Id: Int,
  masteries: List[Mastery],
  participantId: Int,
  timeline: ParticipantTimeline,
  championId: Int,
  runes: List[Rune]
)

object Participant {
  def transform(obj: JObject): JValue = {
    obj.transformField {
      case JField("stats", s) =>
        JField("stats", ParticipantStats.jsonFromParticipants(s.asInstanceOf[JObject]))
    }
  }
}
