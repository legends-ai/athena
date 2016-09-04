package ai.legends.athena.sum

import org.apache.spark.SparkContext._
import org.apache.spark.rdd.RDD
import io.asuna.proto.match_filters.MatchFilters
import io.asuna.proto.match_sum.MatchSum
import ai.legends.athena.data.Match
import ai.legends.athena.sum.MatchSumGroup._

object Permuter {

  def permuteMatches(matches: RDD[(Match, Long)]): RDD[(MatchSumRow)] = {
    matches.flatMap { case (m, rank) =>
      m.participants.map((p) => MatchSumRow.fromData(m, p, rank))
    }
  }

  def groupPermutations(rows: RDD[(MatchSumRow)]): RDD[(MatchSumRow)] = {
    val aggregated = rows.map(row => (row.filters, row)).aggregateByKey(MatchSum())(
      // Add to the match sum object
      _ + _.sum,
      // Add match sums together
      _ + _
    )
    aggregated.map { case (filters, sum) => MatchSumRow(filters, sum) }
  }

}