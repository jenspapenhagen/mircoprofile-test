# microprofile-test
testing/playing around with microprofile with javaEE 8.0
how big its get
and checking for problems.


# Build
mvn clean install payara-micro:bundle

# RUN
mvn payara-micro:start

# Testing the REST API
curl -i -H "Accept: application/json" http://localhost:8080/resources/echo/test
