#!/usr/bin/env bash

# default environment
if [ -z ${ATHENA_ENVIRONMENT} ]; then ATHENA_ENVIRONMENT="dev"; fi

cqlsh -f athena.$ATHENA_ENVIRONMENT.cql
cqlsh -f match_sum.cql
cqlsh -f matches.cql
cqlsh -f rankings.cql
