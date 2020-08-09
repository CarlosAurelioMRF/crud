FROM openjdk:8-jre-alpine

EXPOSE 8080

ENV SPRING_DATA_MONGODB_URI=mongodb+srv://crud:ZdP0cK9wqw3HzLgN@savepass-euqn6.mongodb.net/DB-CRUD?retryWrites=true&w=majority

COPY target/crud-0.0.1-SNAPSHOT.jar /app.war

CMD ["/usr/bin/java", "-jar", "/app.war"]