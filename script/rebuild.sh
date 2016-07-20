#!/usr/bin/env bash

cd `dirname $0`/../
sbt clean compile assembly
