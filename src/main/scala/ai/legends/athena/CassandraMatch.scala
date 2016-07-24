package ai.legends.athena

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
    parse(this.body).extract[Match]
  }

}
