FROM jetty:12.0.23-jdk24-eclipse-temurin

RUN java -jar /usr/local/jetty/start.jar --add-module=ee9-deploy,ee9-jsp

COPY ./target/webapp.war /var/lib/jetty/webapps/ROOT.war
