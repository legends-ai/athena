package ai.legends.athena.matches

import org.json4s.JsonAST.{ JObject, JValue }
import org.json4s.JsonDSL._

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
    val newStats = ParticipantStats.jsonFromParticipants(obj \ "stats")
    return obj ~ ("stats" -> newStats)
  }
}
