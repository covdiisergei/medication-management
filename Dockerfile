ARG JAVA_VERSION=17
FROM openjdk:${JAVA_VERSION}
ADD target/MedicationManagement-0.0.1-SNAPSHOT.jar medication-management.jar
EXPOSE 8080/tcp
ENTRYPOINT ["java","-jar","medication-management.jar"]