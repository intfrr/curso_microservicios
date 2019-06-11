#!/usr/bin/env sh

cd api-clientes-microservicio
./gradlew clean buildImage 

docker-compose up --build

docker-compose stop
docker-compose kill
docker-compose rm -f
