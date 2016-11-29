package ai.legends.athena

import io.asuna.proto.match_filters.MatchFilters
import io.asuna.proto.match_sum.MatchSum

/**
  * Allows directly writing to Cassandra from RDD.
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

  def apply(filters: MatchFilters, sum: MatchSum): CassandraMatchSum = {
    CassandraMatchSum(
      championId = filters.championId,
      enemyId = filters.enemyId,
      patch = filters.patch,
      tier = filters.tier,
      region = filters.region.value,
      role = filters.role.value,
      queue = filters.queue.value,
      matchFilters = filters.toByteArray,
      matchSum = sum.toByteArray
    )
  }

}
