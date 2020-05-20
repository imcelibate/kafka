FROM maven:3-openjdk-8
#CMD ["java","-jar","/usr/src/app/target/kafka-0.0.1-SNAPSHOT.jar"]
RUN mvn clean