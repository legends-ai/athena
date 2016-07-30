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
  matchId: Int
)

object Match {

  def fromJSON(json: String): Match = {
    fromJValue(adaptJSON(parse(json)))
  }

  def adaptJSON(json: JValue): JValue = {
    json.asInstanceOf[JObject] mapField {
      case ("participants", _) =>
        ("participants" -> transformParticipants(json \ "participants"))
      case other => other
    }
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
