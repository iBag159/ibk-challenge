FROM openjdk:8-alpine
ADD target/challenge.jar /usr/share/app.jar
EXPOSE 8080
ENTRYPOINT ["/usr/bin/java", "-jar", "/usr/share/app.jar"]