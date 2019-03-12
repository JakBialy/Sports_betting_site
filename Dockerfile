FROM openjdk:8u191-jre-alpine3.9

MAINTAINER Jakub Biały "bialyj1@gmail.com"

EXPOSE 8080

WORKDIR usr/local/bin

COPY target/sports-betting-site-0.0.1-SNAPSHOT.jar webapp.jar

CMD ["java", "-jar", "webapp.jar"]