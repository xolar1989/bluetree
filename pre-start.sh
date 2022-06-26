#!/bin/bash


#create jars
mvn clean install

 # start docker compose
docker-compose up -d --force-recreate --no-deps --build

# remove docker compose
docker-compose down