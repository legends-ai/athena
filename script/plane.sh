#!/usr/bin/env bash

# Script to run stuff from the plane
spark-submit \
    --class ai.legends.athena.Main \
    --master local \
    ../target/scala-2.10/legendsai-athena-assembly-0.0.1.jar
