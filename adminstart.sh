#!/bin/bash

#tsofim-service
cd admin-servise
# Pull new changes
git pull
# Prepare Jar
mvn clean package docker:build docker:push

cd ..
cd docker
docker-compose down
docker-compose up


