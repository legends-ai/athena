package ai.legends.athena

import ai.legends.athena.matches.Match
import ai.legends.athena.matches.Participant
import org.json4s._
import org.json4s.JsonDSL._
import org.json4s.jackson.JsonMethods._

case class CassandraMatch(
  id: String,
  region: String,
  body: String,
  rank: Long,
  patch: String
) {

  /** toMatch converts the Cassandra match to JSON. */
  def toMatch(): Match = {
    implicit val formats = DefaultFormats
    val m = parse(this.body)
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
