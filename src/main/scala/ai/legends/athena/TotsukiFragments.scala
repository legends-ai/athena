package ai.legends.athena

import org.apache.spark.SparkContext
import org.apache.spark.rdd.RDD
import scala.collection.JavaConverters._
import com.amazonaws.services.s3.AmazonS3Client
import io.asuna.proto.bacchus.BacchusData.RawMatch

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
  def makeRDD(sc: SparkContext, fragments: List[String]): RDD[RawMatch] = {
    // Parallelize the fragments so we can parse on each node
    val frags = sc.parallelize(fragments, 16)

    // Read the streams of each fragment
    frags.map { f =>
      val obj = s3.getObject(cfg.totsukiBucket, f)
      RawMatch.streamFromDelimitedInput(obj.getObjectContent).toList
    }.flatMap(identity)
  }

}
