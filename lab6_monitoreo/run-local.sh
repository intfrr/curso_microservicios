#!/usr/bin/env bash

cd clientes
./generaImagen.sh

cd ../

cd creditos
./generaImagen.sh

cd ../

cd discovery-server
./generaImagen.sh


cd ../

docker-compose up --build

docker-compose stop
docker-compose kill
docker-compose rm -f
