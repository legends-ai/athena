package ai.legends.athena.sum

import org.apache.spark.SparkContext._
import org.apache.spark.rdd.RDD
import io.asuna.proto.match_filters.MatchFilters
import io.asuna.proto.match_sum.MatchSum
import io.asuna.proto.charon.CharonData.Match
import io.asuna.asunasan.legends.MatchSumHelpers._
import spire.implicits._

object Permuter {

  def permuteMatches(matches: RDD[(Match, Long)]): RDD[MatchSumRow] = {
    matches.flatMap { case (m, rank) =>
      m.participantInfo.map((p) => MatchSumRow.fromData(m, p, rank))
    }
  }

  def groupPermutations(rows: RDD[MatchSumRow]): RDD[MatchSumRow] = {
    val aggregated = rows.map(row => (row.filters, row.sum)).aggregateByKey(MatchSum())(
      // Add to the match sum object
      _ |+| _,
      // Add match sums together
      _ |+| _
    )
    aggregated.map { case (filters, sum) => MatchSumRow(filters, sum) }
  }

  /**
    * Calculates MatchSumRows for certain filters being nil.
    */
  def additionalPermutations(rows: RDD[MatchSumRow]): RDD[MatchSumRow] = {
    val noEnemy = rows.map(row => (row.filters.update(_.enemyId := -1), row.sum))
    val noEnemySums = noEnemy.aggregateByKey(MatchSum())(
      _ |+| _,
      _ |+| _
    )
    noEnemySums.map { case (filters, sum) => MatchSumRow(filters, sum) }
  }

}
