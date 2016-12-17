package ai.legends.athena

import RDDImplicits._
import io.asuna.proto.bacchus.BacchusData.RawMatch
import org.apache.spark.sql.SparkSession
import org.apache.spark.{ SparkConf, SparkContext }
import scala.util.{ Success, Try }

object Main {

  def main(args: Array[String]) = {
    // First, let's parse the config.
    val cfg = Config.mustParse(args)

    // Next, let's set up our S3 client.
    val s3 = new S3Browser(cfg)

    // TODO(igm): check for presence of lock file

    // We will then fetch all Totsuki fragment names from S3.
    val fragments = s3.fetchObjects()

    val sc = new SparkContext(cfg.sparkConf)
    val sql = SparkSession.builder().config(cfg.sparkConf).getOrCreate()
    import sql.implicits._

    // Fetch all of our Parquet files from S3
    val rawRdd = sql.read.parquet(fragments: _*).rdd
    val rdd = rawRdd.map(_(0).asInstanceOf[Array[Byte]])

    // Parse matches from protobuf and only keep the successful
    val parsedMatches = rdd
      .map(data => Try { RawMatch.parseFrom(data) })
      .collect {
        case Success(x) => x
      }

    val matchRanks = parsedMatches.map { rawMatch =>
      (rawMatch.data, rawMatch.rank)
    }.collect {
      case (Some(m), Some(rank)) => (m, rank)
    }

    // Create participant rows for each
    val participants = matchRanks.flatMap { case (m, rank) =>
      m.participantInfo.map(p => Participant(m, p, rank))
    }

    // Calculate all of the filters/sums for each participant
    val matchesRDD = participants.map(_.tuple).normalize

    // Write to cassandra
    matchesRDD.saveToCassandra(cfg.outKeyspace, cfg.outTable)

    // TODO(igm): write lock file

    println("Wrote " + matchesRDD.count() + " match sums.")

    // stop spark context when we are done
    sc.stop()
  }

}
