docker run -d -p 33060:3306 --name mysql-db -e MYSQL_ROOT_PASSWORD=secret mysql


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



