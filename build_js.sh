#!/usr/bin/env bash
./gradlew client:browserProductionWebpack

cp -r client/build/distributions/ docs/
