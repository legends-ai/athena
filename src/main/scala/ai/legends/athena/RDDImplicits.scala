package ai.legends.athena

import com.datastax.spark.connector.AllColumns
import io.asuna.proto.match_filters.MatchFilters
import io.asuna.proto.match_sum.MatchSum
import org.apache.spark.rdd.RDD
import cats.kernel.Monoid
import scala.reflect.ClassTag
import com.datastax.spark.connector._
import cats.implicits._
import io.asuna.asunasan.legends.MatchSumHelpers._

object RDDImplicits {

  /**
    * Implicit class that manipulates the MatchSumRDD.
    */
  implicit class MatchSumRDD(
    rdd: RDD[(MatchFilters, MatchSum)]
  )(implicit kt: ClassTag[MatchFilters], vt: ClassTag[MatchSum], ord: Ordering[MatchFilters] = null) {

    /**
      * Combines all values of the RDD by filters.
      * WARNING: this is not memoized. Calling it twice will run it twice!
      */
    def combineByFilters: RDD[(MatchFilters, MatchSum)] = {
      rdd.reduceByKey(_ |+| _)
    }

    /**
      * Combines sums without taking enemies into account.
      */
    def withoutEnemies: RDD[(MatchFilters, MatchSum)] = {
      rdd.map { case (filters, sum) =>
        (filters.update(_.enemyId := -1), sum)
      }
    }

    /**
      * Returns the full set of permutations we want to calculate.
      */
    def full = {
      rdd.union(withoutEnemies)
    }

    /**
      * This does everything. I couldn't think of a good method name.
      */
    def normalize: RDD[(MatchFilters, MatchSum)] = {
      rdd.combineByFilters.union(withoutEnemies.combineByFilters)
    }

    /**
      * Saves this RDD to cassandra.
      */
    def saveToCassandra(keyspace: String, table: String) = {
      rdd.map { case (filters, sum) =>
        CassandraMatchSum(filters, sum)
      }.saveToCassandra(keyspace, table, AllColumns)
    }

  }

}
