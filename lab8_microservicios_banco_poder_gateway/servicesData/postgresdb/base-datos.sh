#!/bin/bash
set -e

psql -v ON_ERROR_STOP=1 --username "$POSTGRES_USER" <<-EOSQL

    CREATE USER admin PASSWORD 'admin';

    CREATE DATABASE seguros_guadalupe OWNER admin;
    GRANT ALL PRIVILEGES ON DATABASE seguros_guadalupe TO admin;


    CREATE DATABASE seguros_guadalupe_administracion OWNER admin;
    GRANT ALL PRIVILEGES ON DATABASE seguros_guadalupe_administracion TO admin;

EOSQL
