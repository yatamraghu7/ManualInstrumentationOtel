package com.microservice.consumer.Consumer.Controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import io.opentelemetry.api.trace.Span;
import io.opentelemetry.api.trace.SpanContext;

@RestController
public class ConsumerController {

	private static final Logger log = LoggerFactory.getLogger(ConsumerController.class);

	protected final RestTemplate restTemplate;

	protected final WebClient webClient;

	public ConsumerController(WebClient.Builder webClientBuilder, RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
		this.webClient = webClientBuilder.baseUrl("http://localhost:8081").build();
	}

	@GetMapping("/consume")
	public String consumeHello() {
		String response = restTemplate.getForObject("http://localhost:8081/hello/fromRestTemplate", String.class);
		log.info(
				"This is a call happend from consumeHello method to producer class controller method i.e. sayHelloToRestPeople");
		log.debug(
				"This is a sample log message at debug level to check if the log contains the traceId and spanId or not");
		return "Consumer received: " + response;
	}

	@GetMapping("/consume/usingWebClient")
	public String consume() {
		Span span = Span.current();
		SpanContext context = span.getSpanContext();
		String traceId = context.getTraceId();
		String spanId = context.getSpanId();
		log.info("The generated traceId and spanId will be [{}] and [{}]", traceId, spanId);
		log.info(
				"This is a call going to happen from consume method to producer class controller method i.e. sayHelloUsingWebClient");
		log.debug(
				"This is a sample log message at debug level to check if the log contains the traceId and spanId or not");
		return this.webClient.get().uri("/hello/fromWebClient").retrieve().bodyToMono(String.class).block();
	}

}
