#!/usr/bin/env bash

cd api-adm-autorizaciones
./generaImagen.sh

cd ../

cd discovery-server
./generaImagen.sh

cd ../

cd monitor-server
./generaImagen.sh

cd ../

cd api-seguros
./generaImagen.sh

cd ../

docker-compose up --build

docker-compose stop
docker-compose kill
docker-compose rm -f
