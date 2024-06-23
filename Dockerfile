FROM openjdk:23-ea-17-jdk-slim-bookworm
WORKDIR /app
COPY /target/PracticeModsenJava-1.0.0.jar /app
ENTRYPOINT ["java", "-jar", "PracticeModsenJava-1.0.0.jar"]