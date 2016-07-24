package ai.legends.athena

import org.apache.spark.{ SparkConf, SparkContext }
import com.datastax.spark.connector._

object Main {
  def main(args: Array[String]) {
    val conf = new SparkConf(true).set("spark.cassandra.connection.host", "127.0.0.1")
    val sc = new SparkContext(conf)
    val rdd = sc.cassandraTable[CassandraMatch]("athena", "matches")
    val matches = rdd.map(x => x.toMatch())
  }
}
