#!/usr/bin/env bash



cd discovery-server
./generaImagen.sh

cd ../

cd api-clientes
./generaImagen.sh

cd ../

docker-compose up --build

docker-compose stop
docker-compose kill
docker-compose rm -f
