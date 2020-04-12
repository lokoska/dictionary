#!/usr/bin/env bash
./gradlew client:myBuildProduction && \
  du -sh client/build/distributions/* && \
  ./gradlew client:myRun
