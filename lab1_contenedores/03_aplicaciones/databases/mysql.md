docker run -d -p 33060:3306 --name mysql-db -e MYSQL_ROOT_PASSWORD=secret mysql

1908130725
$ docker run -d -p 33062:3306 --name mysql-db-2 -e MYSQL_ROOT_PASSWORD=secret mysqlç

https://github.com/passbolt/passbolt_docker/issues/103
https://blog.toshima.ru/2018/09/16/docker-run-mysql.html
https://stackoverflow.com/questions/49194719/authentication-plugin-caching-sha2-password-cannot-be-loaded
https://medium.com/@clerton/how-to-connect-to-mysql-docker-image-outside-the-container-21aa7f259e72

ORIGINAL:
$ docker run -d --rm --name=mysql -e MYSQL_ROOT_PASSWORD=password -e MYSQL_DATABASE=dbname -p 3306:3306 mysql:8.0.15 --default-authentication-plugin=mysql_native_password

NEW:
$ docker run -d --name=mysql-db-2 -e MYSQL_ROOT_PASSWORD=secret -e MYSQL_DATABASE=test -p 33062:3306 mysql --default-authentication-plugin=mysql_native_password


    -d: Deatached Mode es la forma en que indicamos que corra en background.
    -p : puerto, el contenedor corre en el puerto 3306 pero hacemos un bind para que lo escuchemos en Host el puerto 33061.
    –name : para no tener que hacer referencia al hash le asignamos un nombre.
    -e : environment le asignamos la contraseña.


docker exec -it mysql-db mysql -p


    exec: indicamos que vamos a pasar un comando.
    -it Modo interactivo.
    mysql -p: es el comando para entrar a la consola de mysql con el usuario root(si has trabajado con mysql en consola es lo mismo).


secret

show databases;

create database bank_ito;

use bank_ito;
show tables;
create table clientes(id int, nombre varchar(500));

show tables;
+--------------------+
| Tables_in_bank_ito |
+--------------------+
| clientes           |
+--------------------+
1 row in set (0.00 sec)

mysql>



