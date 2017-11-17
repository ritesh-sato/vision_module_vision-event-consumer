FROM java:8
VOLUME /tmp
COPY vision-event-consumer-0.0.1-SNAPSHOT.jar app.jar
COPY client.truststore.jks /etc/ssl/private/
COPY client.keystore.jks /etc/ssl/private/
RUN sh -c 'touch /app.jar'
ENV JAVA_OPTS=""
CMD ["java","-jar","/app.jar" ]
