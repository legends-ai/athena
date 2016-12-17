package ai.legends.athena

import RDDImplicits._
import org.apache.spark.{ SparkConf, SparkContext }

object Main {

  def main(args: Array[String]) = {
    // First, let's parse the config.
    val cfg = Config.mustParse(args)

    // Next, let's set up our S3 client.
    val s3 = new S3Browser(cfg)

    // TODO(igm): check for presence of lock file

    val sc = new SparkContext(cfg.sparkConf)

    // We will then fetch all Totsuki fragment names from S3.
    val fragments = s3.fetchObjects()

    // Next, let's get our raw matches RDD.
    val parsedMatches = s3.buildMatchesRDD(fragments)

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
