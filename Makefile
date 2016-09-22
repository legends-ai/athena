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
	aws s3 cp ./target/scala-2.11/legendsai-athena-assembly-0.0.1.jar s3://asuna-spark-jobs/legendsai-athena/legendsai-athena-latest.jar

dcos-run:
	dcos spark run --submit-args='-Dspark.mesos.coarse=true --driver-cores 1.75 --driver-memory 5120M --class ai.legends.athena.Main https://s3-us-west-2.amazonaws.com/asuna-spark-jobs/legendsai-athena/legendsai-athena-latest.jar cassandra.marathon.mesos'
