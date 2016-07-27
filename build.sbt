name := "legendsai-athena"
version := "0.0.1"
scalaVersion := "2.10.6"

libraryDependencies ++= Seq(
  "org.apache.spark" %% "spark-core" % "1.6.2" % "provided",
  "org.apache.spark" %% "spark-sql" % "1.6.2" % "provided",
  "com.datastax.spark" %% "spark-cassandra-connector" % "1.6.0",

  // Scalatest
  "org.scalactic" %% "scalactic" % "2.2.6",
  "org.scalatest" %% "scalatest" % "2.2.6" % "test"
)

mainClass in assembly := Some("ai.legends.athena.Main")

assemblyMergeStrategy in assembly := {
  case x if x contains "netty" => MergeStrategy.first
  case x =>
    val oldStrategy = (assemblyMergeStrategy in assembly).value
    oldStrategy(x)
}
