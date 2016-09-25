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
	aws s3 cp --acl public-read ./target/scala-2.11/legendsai-athena-assembly-0.0.1.jar s3://asuna-spark-jobs/legendsai-athena/legendsai-athena-latest.jar

dcos-run:
	dcos spark run --submit-args='-Dspark.mesos.coarse=true --num-executors 1 --executor-cores 1 --executor-memory 3G -Dspark.cassandra.connection.host=node-0.cassandra.mesos --class ai.legends.athena.Main https://s3-us-west-2.amazonaws.com/asuna-spark-jobs/legendsai-athena/legendsai-athena-latest.jar'
