FROM openjdk:8u151-jre-slim
MAINTAINER jovaniac@gmail.com"
COPY /build/libs/servicio-cliente.jar /opt/servicio-cliente.jar
EXPOSE 8081
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/opt/servicio-cliente.jar"]
