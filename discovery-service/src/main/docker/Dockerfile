FROM frolvlad/alpine-oraclejdk8:slim
VOLUME /tmp
EXPOSE 8761
RUN echo 'hosts: files mdns4_minimal [NOTFOUND=return] dns mdns4' >> /etc/nsswitch.conf
ADD discovery-service-0.0.1-SNAPSHOT.jar app.jar
RUN sh -c 'touch /app.jar'
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]