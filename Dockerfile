FROM openjdk:23-ea-17-jdk-slim-bookworm
WORKDIR /app
COPY /target/PracticeModsenJava-0.0.1-SNAPSHOT.jar /app
ENTRYPOINT ["java", "-jar", "PracticeModsenJava-1.0.0.jar"]