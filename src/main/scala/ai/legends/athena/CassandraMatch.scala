package ai.legends.athena

import ai.legends.athena.matches.Match
import ai.legends.athena.matches.Participant
import org.json4s.JsonDSL._
import org.json4s.JsonAST.JArray
import org.json4s._
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
    val m = parse(this.body).asInstanceOf[JObject]
    val parts = (m \ "participants").asInstanceOf[JArray]
    println(parts \ "0")
    val transformed = m ~ ("participants" -> parts.map(x => {
      println(x)
      // Participant.transform(x.asInstanceOf[JObject])
      return null
    }))
    transformed.extract[Match]
  }

}
