package ai.legends.athena

import io.asuna.proto.charon.CharonData.Match
import scala.util.Try

/**
  * A match object, straight outta Cassandra.
  */
case class CassandraMatch(
  id: String,
  region: String,
  rank: Long,
  patch: String,
  data: Array[Byte]
) {

  /**
    * Parse the protobuf.
    */
  def parse: Try[(Match, Long)] = Try {
    (Match.parseFrom(data), rank)
  }

}
