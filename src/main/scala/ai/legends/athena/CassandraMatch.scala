package ai.legends.athena

import io.asuna.proto.charon.CharonData.Match

case class CassandraMatch(
  id: String,
  region: String,
  rank: Long,
  patch: String,

  data: Array[Byte]
) {

  /** toMatch converts the Cassandra match to a Match object. */
  def toMatch(): Match = {
    Match.parseFrom(data)
  }

}
