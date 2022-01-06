FROM tomcat:9.0.56-jdk11-openjdk-buster

ADD bruush.war /usr/local/tomcat/webapps/

EXPOSE 8080
CMD ["catalina.sh", "run"]