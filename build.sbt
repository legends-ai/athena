name := "legendsai-athena"
version := "0.0.1"
scalaVersion := "2.10.5"

libraryDependencies ++= Seq(
 "org.apache.spark" %% "spark-core" % "1.2.0" % "provided",
 "com.datastax.spark" %% "spark-cassandra-connector" % "1.2.0"
)

mainClass in assembly := Some("ai.legends.athena.Main")
