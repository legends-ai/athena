package ai.legends.athena

import org.apache.spark.rdd.RDD
import org.apache.spark.sql.SparkSession
import scala.collection.JavaConverters._
import com.amazonaws.services.s3.AmazonS3Client
import io.asuna.proto.bacchus.BacchusData.RawMatch
import scala.util.{ Success, Try }

/**
  * Abstraction over Totsuki fragments in S3.
  */
class TotsukiFragments(cfg: Config) {

  val s3 = new AmazonS3Client()

  val path = s"${cfg.region}/${cfg.version}/"

  /**
    * Fetches a list of Totsuki-generated file paths.
    * TODO(igm): maybe support multiple versions being parsed at once?
    * need to see if we actually need less granularity.
    */
  def list(): List[String] = {
    val objects = s3.listObjects(cfg.totsukiBucket, path).getObjectSummaries
    objects.iterator().asScala.map(_.getKey).toList
  }

  /**
    * Builds the Matches RDD from S3.
    */
  def makeRDD(fragments: List[String]): RDD[RawMatch] = {
    val sql = SparkSession.builder().config(cfg.sparkConf).getOrCreate()
    import sql.implicits._

    // Fetch all of our Parquet files from S3
    val rawRdd = sql.read.parquet(fragments: _*).rdd
    val rawBytesRdd = rawRdd.map(_(0).asInstanceOf[Array[Byte]])

    // Parse matches from protobuf and only keep the successful
    rawBytesRdd
      .map(data => Try { RawMatch.parseFrom(data) })
      .collect {
      case Success(x) => x
    }
  }

}
