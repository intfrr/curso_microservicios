gcloud container clusters get-credentials standard-cluster-1  --zone us-central1-a --project  true-hook-237600

estandard-cluster-1 es tu cluster

— zone us-centra1-a indicamos la zona donde se desplegará el cluster

— project nombre del proyecto de google



Validamos el cluster con:

 kubectl cluster-info

kubectl get nodes

kubectl get pods --all-namespaces


Deploy y Services de MicroServicios

kubectl apply -f kubernetes/

Deploy y Service de Clientes

apiVersion: extensions/v1beta1
kind: Deployment
metadata:
  name: servicio-cliente
  labels:
    app: servicio-cliente
spec:
  replicas: 1
  strategy:
    type: RollingUpdate
    rollingUpdate:
      maxSurge: 2
      maxUnavailable: 0
  selector:
    matchLabels:
      app: servicio-cliente
  template:
    metadata:
      labels:
        app: servicio-cliente
    spec:
      containers:
        - name: servicio-cliente
          image: jovaniac/servicio-cliente:0.0.1-snapshot
          ports:
            - containerPort: 8081
              protocol: TCP
          imagePullPolicy: Always
          resources:
          #solicitud de memoria
            requests:
              memory: "256Mi"
              cpu: "250m"
          #limite de memoria
            limits:
              memory: "1024Mi"
              cpu: "500m"   
---
apiVersion: autoscaling/v1
kind: HorizontalPodAutoscaler
metadata:
  name: servicio-cliente
spec:
  maxReplicas: 5
  minReplicas: 1
  targetCPUUtilizationPercentage: 70
  scaleTargetRef:
    apiVersion: extensions/v1beta1
    kind: Deployment
    name: servicio-cliente

Importante la siguiente configuración, para especificar la asignación de CPU y RAM para la aplicación interna del contenedor, en este caso microservicios con Spring Boot. estableciendo la memoria inicial y el máximo.

#solicitud de memoria
            requests:
              memory: "256Mi"
              cpu: "250m"
          #limite de memoria
            limits:
              memory: "1024Mi"
              cpu: "500m"

Donde en requests se especifica la memoria solicitada al contenedor, limits indicamos cual seria el limite para poder escalar.

Ahora, especificamos cuanto queremos ESCALAR, cuantas REPLICAS se crearán al momento de hacer SCALING.

spec:
  maxReplicas: 5
  minReplicas: 1
  targetCPUUtilizationPercentage: 70
  scaleTargetRef:
    apiVersion: extensions/v1beta1
    kind: Deployment
    name: servicio-cliente


Se indica cuales serán el número de replicas cuando se ESCALE maxReplicas, y a cuantas va decrementar Scaling DOWN minReplicas. así mismo cuanto será el porcentaje de CPU usado para iniciar el SCALING UP targetCPUUtilizationPercentage, en este caso lo hacemos por CPU 70%.

    Por qué 70%, no esperamos a escalar cuando nuestra aplicación ya este apunto de colapsar NO?


Service Discovery de Clientes

apiVersion: v1
kind: Service
metadata:
  name: servicio-cliente
  labels:
    app: servicio-cliente
spec:
  spec:
  type: LoadBalancer
  ports:
  - port: 8081
    name: http
  selector:
    app: servicio-cliente

kubectl apply -f kubernetes/


kubectl get pods,svc,hpa — all-namespaces

Vemos que en la columna TARGET, indica cual es el portentaje para que ESCALE el POD en este caso 70%, y que el estado actual es DESCONOCIDO, pero aqui veremos el porcentaje de la memoria USADA.

Dejamos pasar algunso segundos y ahora veremos reflejado el siguiente TARGET 0% / 70%.

kubectl logs -f servicio-cliente-669bd54644-zbwmf


Verificamos que ya nos haya aprovicionado la IP publica, la ip que nos da es 34.66.232.240.


http://34.66.232.240:8081/api/v1/clientes

payload de entrada:

{
 “apellidoMaterno”: “cabrera”,
 “apellidoPaterno”: “arzate”,
 “direccion”: “test”,
 “edad”: 29,
 “email”: “jovaniac@gmail.com”,
 “genero”: “h”,
 “nombre”: “jovani”
}

respuesta, vemos que genera un folio de creación del cliente.

{
    "folioCliente": "-1804035403",
    "nombre": "jovani",
    "apellidoPaterno": "arzate",
    "apellidoMaterno": "cabrera",
    "email": "jovaniac@gmail.com",
    "direccion": "test",
    "genero": "h",
    "edad": 29
}

 kubectl logs -f servicio-cliente-669bd54644-zbwmf

 wrk -t1 -c1 -d30s http://34.66.232.240:8081/api/v1/clientes

 El HPA vía Heapster comienza a actualiza el uso de CPU.

 El targer indica que ya sobrepaso del ?

 wrk -t100 -c100 -d300s http://34.66.232.240:8081/api/v1/clientes


 Se crean REPLICAS y tenemos 3 PODs, atendiendo cada solicitud de forma balanceada.