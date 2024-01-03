FROM ubuntu:latest
LABEL authors="ThienPhu"
# deploys a simple web application with a Dockerfile
# deploy java application with docker file and add tomcat server
# install java
RUN apt-get update && apt-get install -y openjdk-20-jdk
# install tomcat
RUN apt-get install -y tomcat8
# copy war file to tomcat
COPY target/*.war /var/lib/tomcat8/webapps/
# expose port 8080
EXPOSE 8080
# run tomcat
CMD ["catalina.sh", "run"]