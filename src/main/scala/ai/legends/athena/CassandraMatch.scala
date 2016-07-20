package ai.legends.athena

import org.json4s._
import org.json4s.jackson.JsonMethods

case class CassandraMatch(
  id: String,
  region: String,
  body: String,
  rank: Long,
  patch: String
) {
  def toMatch(): Match = Match(JsonMethods.parse(this.body, false))
}
