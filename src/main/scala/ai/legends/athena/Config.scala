package ai.legends.athena

import buildinfo.BuildInfo
import io.asuna.asunasan.legends.AthenaLockManager
import org.apache.spark.SparkConf
import scopt.OptionParser

case class Config(
  region: String = "na",
  version: String = "",
  totsukiBucket: String = "totsuki_fragments",
  lockBucket: String = "athena_locks",
  outKeyspace: String = "athena_partial_sums",
  outTable: String = "partial_sums"
) {

  lazy val sparkConf = new SparkConf(true).setAppName(BuildInfo.name)

  lazy val lockMgr = new AthenaLockManager(lockBucket, region, version)

}

object Config {

  val parser = new OptionParser[Config](BuildInfo.name) {

    head(BuildInfo.name, BuildInfo.version)

    opt[String]("region")
      .text("The region we're reading matches from. Note: this is not the S3 region. Defaults to `na`.")
      .valueName("<na|euw|eune|...>")
      .action((x, c) => c.copy(region = x))

    opt[String]("version")
      .text("The match version we want to read, e.g. 6.22.1.")
      .valueName("<version>")
      .action((x, c) => c.copy(version = x)).required()

    opt[String]("totsuki_bucket")
      .text("The name of the S3 bucket we are reading Totsuki fragments from. Defaults to `totsuki_fragments`.")
      .valueName("<bucket>")
      .action((x, c) => c.copy(totsukiBucket = x))

    opt[String]("lock_bucket")
      .text("The name of the S3 bucket we are reading/writing lock files from. Defaults to `athena_locks`.")
      .valueName("<bucket>")
      .action((x, c) => c.copy(lockBucket = x))

    opt[String]("out_keyspace")
      .text("The output Cassandra keyspace of Athena.")
      .valueName("<keyspace>")
      .action((x, c) => c.copy(outKeyspace = x))

    opt[String]("out_table")
      .text("The output Cassandra table of Athena.")
      .valueName("<table>")
      .action((x, c) => c.copy(outTable = x))

  }

  def mustParse(args: Array[String]): Config = {
    val result = parser.parse(args, Config())
    if (!result.isDefined) {
      // couldn't parse
      sys.exit(0)
    }
    result.get
  }

}
