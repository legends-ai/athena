package ai.legends.athena

import io.asuna.asunasan.legends.MatchSumHelpers._
import RDDImplicits._
import org.apache.spark.{ SparkConf, SparkContext }
import com.datastax.spark.connector._
import scala.util.Success

object Main {

  def main(args: Array[String]) = {
    val conf = new SparkConf(true)
    val sc = new SparkContext(conf)
    val rdd = sc.cassandraTable[CassandraMatch]("athena", "matches_serialized")

    // Parse matches from protobuf and only keep the successful
    val parsedMatches = rdd.map(_.parse) collect {
      case Success(x) => x
    }

    // Create participant rows for each
    val participants = parsedMatches.flatMap { case (m, rank) =>
      m.participantInfo.map(p => Participant(m, p, rank))
    }

    // Calculate all of the filters/sums for each participant
    val matchesRDD = participants.map(_.tuple).combineByFilters.full

    // Write to cassandra
    matchesRDD.saveToCassandra("athena_out", "match_sums")

    println("Wrote " + matchesRDD.count() + " match sums.")

    // stop spark context when we are done
    sc.stop()
  }
}
