rebuild:
	sbt clean compile assembly

run_spark:
	spark-submit \
			--class ai.legends.athena.Main \
			--master local[4] \
			target/scala-2.11/legendsai-athena-assembly-0.0.1.jar

syncproto:
	cd src/main/protobuf && git pull origin master

deploy:
	aws s3 cp ./target/scala-2.11/legendsai-athena-assembly-0.0.1.jar s3://asuna-spark-jobs/legendsai-athena/legendsai-athena-latest.jar
