name := "athena"
organization := "io.asuna"
version := "0.1.0-SNAPSHOT"
scalaVersion := "2.11.8"

libraryDependencies ++= Seq(
  "org.apache.spark" %% "spark-core" % "2.0.0" % "provided",
  "org.apache.spark" %% "spark-sql" % "2.0.0" % "provided",
  "com.datastax.spark" %% "spark-cassandra-connector" % "2.0.0-M3",

  // Asuna standard lib
  "io.asuna" %% "asunasan" % "0.1.1-SNAPSHOT" ,
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
assemblyJarName in assembly := "athena-assembly.jar"

awsProfile := "asuna"
s3overwrite := true
s3region := com.amazonaws.services.s3.model.Region.US_West

// Publishing
publishMavenStyle := false
publishTo := {
  Some(s3resolver.value("Aincrad", s3("aincrad.asuna.io")) withIvyPatterns)
}

// Resolver
resolvers ++= Seq[Resolver](
  s3resolver.value("Aincrad", s3("aincrad.asuna.io"))
)

// Docker stuff
enablePlugins(DockerPlugin)

dockerfile in docker := {
  // The assembly task generates a fat JAR file
  val artifact: File = assembly.value
  val artifactTargetPath = s"/app/${artifact.name}"

  new Dockerfile {
    from("gettyimages/spark:2.0.2-hadoop-2.7")
    add(artifact, artifactTargetPath)
    entryPoint("spark-submit",
               "--conf", "spark.cassandra.connection.host=127.0.0.1",
               "--class", "ai.legends.athena.Main",
               "--master", "local[4]", artifactTargetPath)
  }
}

val base = "096202052535.dkr.ecr.us-west-2.amazonaws.com"
imageNames in docker := Seq(
  // Sets the latest tag
  ImageName(s"${base}/${name.value}:latest"),
  ImageName(s"${base}/${name.value}:${version.value}")
)
