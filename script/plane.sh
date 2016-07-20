#!/usr/bin/env bash

DIR=`dirname $0`

# Script to run stuff from the plane
spark-submit \
    --class ai.legends.athena.Main \
    --master local[4] \
    $DIR/../target/scala-2.10/legendsai-athena-assembly-0.0.1.jar
