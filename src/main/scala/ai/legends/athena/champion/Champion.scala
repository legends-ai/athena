package ai.legends.athena.champions

import ai.legends.athena.Match
import com.datastax.spark.connector._
import org.apache.spark.SparkContext._
import com.datastax.spark.connector.rdd.CassandraTableScanRDD
import org.apache.spark.rdd.RDD

case class Champion(
  banRate: Double
)

object Champion {

  def calculateAll(rdd: RDD[Match]): Set[Champion] = {
    val champBans = rdd.flatMap(_.teams).flatMap(_.bans)
      .map(x => (x.championId, 1)).reduceByKey(_ + _).collectAsMap()

    println(champBans)
    return null
  }

}
