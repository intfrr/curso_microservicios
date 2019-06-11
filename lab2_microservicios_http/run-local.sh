#!/usr/bin/env bash

cd api-seguros
./generaImagen.sh

cd ../

cd api-adm-autorizaciones
./generaImagen.sh

docker-compose up --build

docker-compose stop
docker-compose kill
docker-compose rm -f
