#!/usr/bin/env bash

DIR=`dirname $0`

spark-submit \
    --class ai.legends.athena.Main \
    --master local[4] \
    $DIR/../target/scala-2.11/legendsai-athena-assembly-0.0.1.jar
