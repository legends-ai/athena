#!/usr/bin/env bash

# Script to run stuff from the plane
spark-submit \
    --jars /Users/ian/.ivy2/cache/com.datastax.spark/spark-cassandra-connector_2.10/jars/spark-cassandra-connector_2.10-1.2.0.jar,/Users/ian/.ivy2/cache/com.datastax.cassandra/cassandra-driver-core/jars/cassandra-driver-core-2.1.5.jar,/Users/ian/.ivy2/cache/com.google.guava/guava/jars/guava-16.0.1.jar \
    --master local \
    ../target/scala-2.10/legendsai-athena_2.10-0.0.1.jar
