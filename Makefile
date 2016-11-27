build:
	sbt clean compile assembly

local-run:
	spark-submit \
			--class ai.legends.athena.Main \
			--master local[4] \
			target/scala-2.11/athena-assembly.jar

syncproto:
	cd src/main/protobuf && git pull origin master

deploy:
	sbt publish

dcos-run:
	dcos spark run --submit-args='-Dspark.cores.max=7 -Dspark.driver.cores=0.99 -Dspark.executor.memory=4G -Dspark.cassandra.connection.host=node-0.cassandra.mesos,node-1.cassandra.mesos,node-2.cassandra.mesos --class ai.legends.athena.Main http://aincrad.asuna.io/io/asuna/athena_2.11/0.1.0-SNAPSHOT/athena_2.11-0.1.0-SNAPSHOT.jar'
