package ai.legends.athena.matches

import ai.legends.athena.aggregates._
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
  timeline: Option[ParticipantTimeline],
  championId: Int,
  runes: List[Rune]
)

object Participant {

  val statsFields = Map(
    "damage" -> classOf[ParticipantDamage],
    "firsts" -> classOf[ParticipantFirsts],
    "items" -> classOf[ParticipantItems],
    "kda" -> classOf[ParticipantKDA],
    "wards" -> classOf[ParticipantWards]
  )

  def transform(obj: JObject): JValue = {
    obj.transformField {
      case JField("stats", s) =>
        JField("stats", jsonFromParticipants(s.asInstanceOf[JObject]))
    }
  }

  def jsonFromParticipants(obj: JObject): JValue = {
    statsFields.foldLeft(obj) {
      case (acc, (k, v)) =>
        acc ~ JField(k, extract(obj,
          v.getDeclaredFields.map(_.getName).toList))
    }
  }

  def extract(obj: JObject, keys: List[String]): JValue = {
    keys.map(key => (key -> (obj \ key))).foldLeft(JObject())(_ ~ _)
  }

}
