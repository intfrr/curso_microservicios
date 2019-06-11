docker info

docker run hello-world

version:

docker -v

Docker version 18.06.1-ce, build e68fc7a

contanedores corriendo:

docker ps


imagenes construidas o descargas:

docker images
docker images ls

detener contenedor:

docker stop <id contenedor>

construir imagen docker:

docker build -t usuario/nombre-imagen:tag .

ejecutar container:

docker run -i -t -p puerto-host:puerto-container usuario/nombre-imagen:tag 

ejecutar container y borra su estado al terminar

docker run -i -t --rm jovaniac/server-app:0.1 /bin/bash


purgar todo el host:

docker system prune -a

remover imagenes y contenedores

rm          Remove one or more containers
rmi         Remove one or more images

ver logs de contenedores en ejecucion:

docker logs <id contenedor>

reinicia contenedor:

docker restart <id contenedor>

estatus del contenedor:
    CONTAINER — ID do Container;
    CPU % — uso de CPU em porcentagem;
    MEM USAGE / LIMIT — Memória usada/Limite que você pode ter setado;
    MEM — uso de memória em porcentagem;
    NET I/O — I/O de Internet;
    BLOCK IO — Outros processos de I/O;

docker stats <id contenedor>

introducirte dentro del contenedor

docker exec -it  <id contenedor> bash

docker exec -it <id contenedor> /bin/bash

manda la señal kill a un container en especifico:

docker kill <id contenedor>
