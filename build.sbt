name := "legendsai-athena"
version := "0.0.1"
scalaVersion := "2.11.8"

libraryDependencies ++= Seq(
  "org.apache.spark" %% "spark-core" % "2.0.0" % "provided",
  "org.apache.spark" %% "spark-sql" % "2.0.0" % "provided",
  "com.datastax.spark" %% "spark-cassandra-connector" % "2.0.0-M3",

  // Asuna standard lib
  "io.asuna" %% "asunasan" % "0.1.0",
  "com.trueaccord.scalapb" %% "scalapb-runtime" % "0.5.39",

  // Scalatest
  "org.scalactic" %% "scalactic" % "2.2.6",
  "org.scalatest" %% "scalatest" % "2.2.6" % "test"
)

mainClass in assembly := Some("ai.legends.athena.Main")

assemblyMergeStrategy in assembly := {
  case x if x contains "netty" => MergeStrategy.first
  case x if x contains "publicsuffix" => MergeStrategy.first
  case x =>
    val oldStrategy = (assemblyMergeStrategy in assembly).value
    oldStrategy(x)
}

s3overwrite := true
s3region := com.amazonaws.services.s3.model.Region.US_West
s3acl := com.amazonaws.services.s3.model.CannedAccessControlList.AuthenticatedRead

// Publishing
publishMavenStyle := false
publishTo := {
  Some(s3resolver.value("Aincrad", s3("aincrad.asuna.io")) withIvyPatterns)
}

// Resolver
resolvers ++= Seq[Resolver](
  s3resolver.value("Aincrad", s3("aincrad.asuna.io"))
)
