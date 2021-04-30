#!/bin/bash

#tsofim-service
cd tsofim-servise
# Pull new changes
git pull
# Prepare Jar
mvn clean package docker:build docker:push

#go to children-service
cd ..
cd children-service
git pull
mvn clean package docker:build docker:push

#go to email-service
cd ..
cd email-service
git pull
mvn clean package docker:build docker:push

#go to eureka-service
cd ..
cd eureka-service
git pull
mvn clean package docker:build docker:push

go to rabbit-service
cd ..
cd rabbit
git pull
mvn clean package docker:build docker:push

go to scheduler-service
cd ..
cd sceduler-service
git pull
mvn clean package docker:build docker:push

go to user-service
cd ..
cd user-service
git pull
mvn clean package docker:build docker:push

go to zuul-service
cd ..
cd zuul-service
git pull
mvn clean package docker:build docker:push

compose start
cd ..
cd docker
docker-compose down
docker-compose up


