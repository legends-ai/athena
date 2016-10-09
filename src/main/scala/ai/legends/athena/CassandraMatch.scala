package ai.legends.athena

import org.json4s._
import org.json4s.JsonDSL._
import org.json4s.jackson.JsonMethods._
import io.asuna.asunasan.legends.riotmatch.Match

case class CassandraMatch(
  id: String,
  region: String,
  body: String,
  rank: Long,
  patch: String
) {

  /** toMatch converts the Cassandra match to a Match object. */
  def toMatch(): Match = {
    implicit val formats = DefaultFormats
    parse(body).extract[Match]
  }

}
