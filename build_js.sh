#!/usr/bin/env bash
./gradlew client:browserProductionWebpack

cp -r client/build/distributions/* docs
date > docs/build_date.txt
du -sh docs/*
