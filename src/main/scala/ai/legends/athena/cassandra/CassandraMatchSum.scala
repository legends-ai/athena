package ai.legends.athena.cassandra

import ai.legends.athena.sum.MatchSumRow
import java.io.ByteArrayOutputStream

/**
  * Cassandra version of MatchSumRow. Allows directly writing to Cassandra from RDD.
  */
case class CassandraMatchSum(
  championId: Int,
  enemyId: Int,
  patch: String,
  tier: Int,
  region: Int,
  role: Int,
  queue: Int,
  matchFilters: Array[Byte],
  matchSum: Array[Byte]
)

object CassandraMatchSum {

  def apply(m: MatchSumRow): CassandraMatchSum = {
    // Serialize protocol buffers
    val matchFiltersBytes = new ByteArrayOutputStream()
    m.filters.writeTo(matchFiltersBytes)
    val matchSumBytes = new ByteArrayOutputStream()
    m.sum.writeTo(matchSumBytes)

    // Generate sum object
    CassandraMatchSum(
      championId = m.filters.championId,
      enemyId = m.filters.enemyId,
      patch = m.filters.patch,
      tier = m.filters.tier,
      region = m.filters.region.value,
      role = m.filters.role.value,
      queue = m.filters.queue.value,
      matchFilters = matchFiltersBytes.toByteArray(),
      matchSum = matchSumBytes.toByteArray()
    )
  }

}
