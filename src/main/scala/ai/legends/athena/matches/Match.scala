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
    val m = parse(json)
    implicit val formats = DefaultFormats

    val transformed = m.asInstanceOf[JObject] ~ ("participants" -> transformParticipants(m \ "participants"))
    println("+\n" * 10)
    println(((m \ "participants")(0) \ "stats") \ "winner")
    println(((transformed \ "participants")(0) \ "stats") \ "winner")
    println(((transformed \ "participants")(1) \ "stats") \ "winner")
    println("+\n" * 10)
    transformed.extract[Match]
  }


  def transformParticipants(json: JValue): JArray = {
    json.asInstanceOf[JArray].transform {
      case obj: JObject => Participant.transform(obj)
    }.asInstanceOf[JArray]
    // json.asInstanceOf[JArray].arr.map {
    //   case obj: JObject => Participant.transform(obj)
    //   case x => x
    // }
  }

}
