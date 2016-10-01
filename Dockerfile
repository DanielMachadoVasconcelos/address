FROM java:8
VOLUME /tmp
ADD target/address.jar app.jar
EXPOSE 8080
RUN bash -c 'touch /app.jar'
ENTRYPOINT ["java","-Dspring.data.mongodb.uri=mongodb://address-dev:123456@ds015325.mlab.com:15325/address", "-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]