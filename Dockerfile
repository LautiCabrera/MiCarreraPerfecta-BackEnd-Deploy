FROM amazoncorretto:8-alpine-jdk 
MAINTAINER lautaro
COPY target/MiCarreraPerfecta-0.0.1-SNAPSHOT.jar MiCarreraPerfecta-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/MiCarreraPerfecta-0.0.1-SNAPSHOT.jar"]
EXPOSE 8080
