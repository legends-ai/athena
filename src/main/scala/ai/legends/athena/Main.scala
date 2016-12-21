package ai.legends.athena

import RDDImplicits._
import io.asuna.asunasan.legends.AthenaLockManager
import io.asuna.proto.athena.AthenaLock
import org.apache.spark.{ SparkConf, SparkContext }

object Main {

  def main(args: Array[String]) = {
    // First, let's parse the config.
    val cfg = Config.mustParse(args)

    // Next, we'll check if the lock is defined. If it is, we still
    // have not run Jibril, so we cannot run Athena.
    if (cfg.lockMgr.fetch().isDefined) {
      println(s"Lock file at ${cfg.lockMgr.path} already exists!")
      sys.exit(1)
    }

    val sc = new SparkContext(cfg.sparkConf)

    // Next, let's set up our S3 client.
    val frags = new TotsukiFragments(cfg)

    // We will then fetch all Totsuki fragment names from S3.
    // This is done independently of the next step because we will use this
    // when building the lock file.
    val fragmentNames = frags.list

    // Next, let's get our RawMatch RDD.
    val parsedMatches = frags.makeRDD(fragmentNames)

    // We will now extract matches and ranks from this RDD.
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

    // Collect unique filters
    val filters = matchesRDD.keys.collect()

    // Write the lock file
    cfg.lockMgr.store(AthenaLock(
      paths = fragmentNames,
      filters = filters
    ))

    println(s"Wrote ${filters.size} match sums.")

    // stop spark context when we are done
    sc.stop()
  }

}
