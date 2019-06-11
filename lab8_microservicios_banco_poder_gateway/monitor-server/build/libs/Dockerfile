FROM openjdk:8u151-jre-slim
MAINTAINER jovaniac@gmail.com
COPY monitor-server.jar /opt/monitor-server.jar
EXPOSE 8090
ENTRYPOINT ["java", "-Djava.awt.headless=true", "-jar", "/opt/monitor-server.jar"]
