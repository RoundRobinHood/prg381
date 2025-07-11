FROM maven:3.9.10-eclipse-temurin-24 AS build

WORKDIR /app
COPY . .

RUN mvn package

FROM openjdk:24-jdk-slim

ARG JETTY_VERSION=11.0.24 # Example version, use the latest Jetty 11
RUN apt-get update && apt-get install -y wget && \
    wget -qO /tmp/jetty.zip https://repo1.maven.org/maven2/org/eclipse/jetty/jetty-distribution/${JETTY_VERSION}/jetty-distribution-${JETTY_VERSION}.zip && \
    unzip /tmp/jetty.zip -d /opt && \
    mv /opt/jetty-distribution-${JETTY_VERSION} /opt/jetty && \
    rm /tmp/jetty.zip && \
    apt-get remove --purge -y wget && \
    apt-get clean && rm -rf /var/lib/apt/lists/*

ENV JETTY_HOME=/opt/jetty
WORKDIR ${JETTY_HOME}

FROM jetty:12.0.23-jdk24-eclipse-temurin

RUN java -jar /usr/local/jetty/start.jar --add-module=ee9-deploy,ee9-jsp

COPY --from=build /app/target/*.war /var/lib/jetty/webapps/ROOT.war
