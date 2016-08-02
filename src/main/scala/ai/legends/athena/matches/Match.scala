package ai.legends.athena.matches

import org.json4s._
import org.json4s.JsonDSL._
import org.json4s.jackson.JsonMethods._

case class Match (
  matchDuration: Int,
  matchVersion: String,
  teams: List[Team],
  platformId: String,
  participants: List[Participant],
  matchMode: String,
  matchType: String,
  season: String,
  mapId: Int,
  participantIdentities: List[ParticipantIdentity],
  timeline: Option[MatchTimeline],
  region: String,
  matchCreation: Int,
  queueType: String,
  matchId: Int,
  division: Int,
  tier: Int
)

object Match {

  def fromJSON(json: String, rank: Long): Match = {
    val obj = adaptJSON(parse(json))
    val withTier = obj ~
      ("tier" -> ((rank >> 16) & 0xffff).toInt) ~
      ("division" -> (rank & 0xffff).toInt)
    fromJValue(withTier)
  }

  def adaptJSON(json: JValue): JObject = {
    json.mapField {
      case ("participants", _) =>
        ("participants" -> transformParticipants(json \ "participants"))
      case other => other
    }.asInstanceOf[JObject]
  }

  def fromJValue(json: JValue): Match = {
    implicit val formats = DefaultFormats
    json.extract[Match]
  }

  def transformParticipants(json: JValue): JArray = {
    json.transform {
      case obj: JObject => Participant.transform(obj)
    }.asInstanceOf[JArray]
  }

}
