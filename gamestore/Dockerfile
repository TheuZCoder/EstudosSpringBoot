FROM ubuntu:lastest AS build

RUN apt-get update && apt-get install -y openjdk-11-jdk

COPY . .

RUN apt-get install maven -y

RUN mvn cleans install

FROM openjdk-17-jdk-slim

EXPOSE 8080

COPY --from=built /target/gamestore.jar /app.jar

ENTRYPOINT ["java","-jar","app.jar"]