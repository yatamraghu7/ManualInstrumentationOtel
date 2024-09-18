# opentelemetryInstrumentation

This is my POC for adapting the opentelemetry instrumentation for micro-services Producer and Consumer using Spring Boot 2.7.1 and open telemetry spring boot starter

You can Run the application from any IDE out of the Box. If you are using command line then use the below commands

Producer Microservice:

java -jar target/Producer-0.0.1-SNAPSHOT.jar \
     -Dotel.service.name=producer-poc \
     -Dotel.traces.exporter=console,otlp \
     -Dotel.metrics.exporter=otlp \
     -Dotel.logs.exporter=otlp

Consumer Microservice:

java -jar target/Consumer-0.0.1-SNAPSHOT.jar \
     -Dotel.service.name=consumer-poc \
     -Dotel.traces.exporter=console,otlp \
     -Dotel.metrics.exporter=otlp \
     -Dotel.logs.exporter=otlp

If you are experimenting with my repository in your local and added telemetry java agent and removed the dependency of open telemetry spring boot starter then run as below

java -javaagent:opentelemetry-javaagent.jar \
     -Dotel.service.name=producer-poc \
     -Dotel.traces.exporter=console,otlp \
     -Dotel.metrics.exporter=otlp \
     -Dotel.logs.exporter=otlp \
     -jar target/Producer-0.0.1-SNAPSHOT.jar

java -javaagent:opentelemetry-javaagent.jar \
     -Dotel.service.name=consumer-poc \
     -Dotel.traces.exporter=console,otlp \
     -Dotel.metrics.exporter=otlp \
     -Dotel.logs.exporter=otlp \
     -jar target/Consumer-0.0.1-SNAPSHOT.jar

This project contains micro-services communications using RestTemplate, WebClient which is working as expected. 
WebSocket Communication between microservices instrumentation is in progress [To be Done]

Testing with Curls can be done as below:

curl -v -X GET http://localhost:8080/consume/usingWebClient \
-H 'accept: */*' \
-H 'Content-Type: application/json' \
-H 'X-B3-TraceId: 01011111001110102222222222222222' \
-H 'X-B3-SpanId: 2222222222222222' \
-H 'X-B3-ParentSpanId: 3333333333333333' \
-H 'X-B3-Sampled: 1'


curl -v -X GET http://localhost:8080/consume/usingWebClient \
-H 'accept: */*' \
-H 'Content-Type: application/json' \
-H 'X-B3-TraceId: 01011111001110102222222222222222' \
-H 'X-B3-SpanId: 2222222222222222' \
-H 'X-B3-ParentSpanId: 3333333333333333' \
-H 'X-B3-Sampled: 1'

Thanks
