# Kubectl

Kubectl es la herramienta cliente para interactuar con el API REST de Kubernetes. Funciona en la terminal (shell) de todos los sistemas operativos.

## Verificar que el cluster de Kubernetes funcione

  $ kubectl cluster-info


## Verificar la configuración de kubectl

  $ kubectl config view

## Desplegar la aplicación en Minikube

  $ kubectl run holamundo --image=jovaniac/server-app:0.1 --port=5000

http://35.222.207.75:5000/

Lo que hará el comando anterior es crear un `Deployment`, se discutirá este componente más tarde. El solo hecho de hacer el despliegue no garantiza que el contenedores pueda ser `descubierto` por los demás contenedores dentro del cluster y mucho menos por otros procesos externos del cluster. El Deployment por ahora solo hace la ejecución del contenedor sin exponer ningún puerto dentro y fuera del cluster.

## Verificar que esta corriendo

  $ kubectl get pods
  NAME                        READY     STATUS    RESTARTS   AGE
  holamundo-2790372988-w2zw4   1/1       Running   0          13m

La salida no será igual, pero similar, lo importante es revisar el `STATUS`.

*IMPORTANTE*: Cada linea de la salida anterior, representa una instancia de algún pod. A esta instancia se le conoce como `Replica`. Lo que significa que, podemos crear varias replicas de un mismo pod para escalar horizontalmente.

## Exponer puertos

Para exponer los puertos, se usan los `Services`. De estos hay de muchos tipos, por ahora haremos uno muy sencillo.

## Ver los distintos servicios que tiene mi cluster

  $ kubectl get services


## Crear un servicio para exponer el Deployment

  $ kubectl expose deployment holamundo --type=LoadBalancer

El tipo de `Service` que estamos creando es `LoadBalancer` este tipo de servicio lo expone fuera del clúster. En los proveedores de nube que admiten `LoadBalancer`, se aprovisionaría una dirección IP externa para acceder al `Service`.

Aquí podría revisar nuevamente los servicios que hay configurados en mi cluster.

  $ kubectl get services

## Obtener una descripción detallada de los pods

Podemos obtener el detalle fino de la configuración de los pos mediante la acción `describe`.+

  $ kubectl describe pod holamundo


Tambien podemos obtener ese detalle para cualquier tipo de recursos de kubernetes, como los servicios:

  $ kubectl describe service holamundo


  ## Forward de puertos

En ocasiones es útil hacer redirección de puertos de forma temporal para acceder a un puerto de un pod. Esto para evitar crear un servicio. La forma en que funciona es: `kubectl port-forward ${nombre_pod} ${puerto_local}:${puerto_contenedor}`

Suponiendo que el nombre del pod de la aplicacion `holamundo` es `holamundo-45676543`, con la siguiente instrucción el tráfico de la maquina local en el puerto `8087` se va a redirigir al puerto `8080` del contenedor que corre en Kubernetes.

  $ kubectl port-forward holamundo-dsjfgsdgf 6000:5000


## Borrar Pods y services

Se puede eliminar la configuración con el comando `kubectl delete ${recurso} {nombre_recurso}`

  $ kubectl delete service holamundo
  $ kubectl delete deployment holamundo
