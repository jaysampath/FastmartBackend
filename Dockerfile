FROM openjdk:8-jdk-alpine
COPY target/FastmartBackend-1.0.0.jar FastmartBackend-1.0.0.jar
ENTRYPOINT ["java","-jar","/FastmartBackend-1.0.0.jar"]