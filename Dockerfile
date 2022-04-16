FROM openjdk:11.0.14.1-oracle
ARG JAR_FILE=target/readingisgood-0.0.1-SNAPSHOT.jar
WORKDIR /opt/app
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","app.jar"]
