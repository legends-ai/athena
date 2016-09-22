rebuild:
	sbt clean compile assembly

local-run:
	spark-submit \
			--class ai.legends.athena.Main \
			--master local[4] \
			target/scala-2.11/legendsai-athena-assembly-0.0.1.jar

syncproto:
	cd src/main/protobuf && git pull origin master

deploy:
	aws s3 cp --acl public-read ./target/scala-2.11/legendsai-athena-assembly-0.0.1.jar s3://asuna-spark-jobs/legendsai-athena/legendsai-athena-asdf.jar

dcos-run:
	dcos spark run --submit-args='-Dspark.mesos.coarse=true --driver-cores 3.5 --driver-memory 10240M -Dspark.cassandra.connection.host=cassandra.marathon.mesos --class ai.legends.athena.Main https://s3-us-west-2.amazonaws.com/asuna-spark-jobs/legendsai-athena/legendsai-athena-asdf.jar'
