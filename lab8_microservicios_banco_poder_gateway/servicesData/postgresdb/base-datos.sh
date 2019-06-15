#!/bin/bash
set -e

psql -v ON_ERROR_STOP=1 --username "$POSTGRES_USER" <<-EOSQL

    CREATE USER admin PASSWORD 'admin';

    CREATE DATABASE banco_poder_creditos OWNER admin;
    GRANT ALL PRIVILEGES ON DATABASE banco_poder_creditos TO admin;
    
    CREATE DATABASE banca_administracion OWNER admin;
    GRANT ALL PRIVILEGES ON DATABASE banca_administracion TO admin;

    CREATE DATABASE banco_poder_empleados OWNER admin;
    GRANT ALL PRIVILEGES ON DATABASE banco_poder_empleados TO admin;

    CREATE DATABASE banco_poder_clientes OWNER admin;
    GRANT ALL PRIVILEGES ON DATABASE banco_poder_clientes TO admin;

EOSQL
