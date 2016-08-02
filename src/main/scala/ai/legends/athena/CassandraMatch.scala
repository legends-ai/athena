package ai.legends.athena

import ai.legends.athena.matches.Match
import ai.legends.athena.matches.Participant

case class CassandraMatch(
  id: String,
  region: String,
  body: String,
  rank: Long,
  patch: String
) {

  /** toMatch converts the Cassandra match to JSON. */
  def toMatch(): Match = {
    Match.fromJSON(body, rank)
  }

}
