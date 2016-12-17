package ai.legends.athena

import scala.collection.JavaConverters._
import com.amazonaws.services.s3.AmazonS3Client

class S3Browser(cfg: Config) {

  val s3 = new AmazonS3Client()

  val path = s"${cfg.region}/${cfg.version}/"

  /**
    * Fetches a list of Totsuki-generated file paths.
    * TODO(igm): maybe support multiple versions being parsed at once?
    * need to see if we actually need less granularity.
    */
  def fetchObjects(): List[String] = {
    val objects = s3.listObjects(cfg.s3bucket, path).getObjectSummaries
    objects.iterator().asScala.map(_.getKey).toList
  }

}
