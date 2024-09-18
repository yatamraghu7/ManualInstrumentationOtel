package com.microservice.producer.Producer.Controller;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import io.opentelemetry.api.trace.Span;
import io.opentelemetry.api.trace.Tracer;

@RestController
public class ProducerController {

	private static final Logger log = LoggerFactory.getLogger(ProducerController.class);

	private SimpMessagingTemplate simpMessagingTemplate;

	@Autowired
	private Tracer tracer;

	@GetMapping("/hello")
	public String sayHello() {
		log.info(
				"At this point we have received a call from the consumer class to consume the message that was produced by sayHello Method");
		log.debug(
				"This is a sample log message at debug level to check if the log contains the traceId and spanId or not");
		return "Hello from Producer !";
	}

	@GetMapping("/hello/fromRestTemplate")
	public String sayHelloToRestPeople() {
		log.info(
				"At this point we have received a call from the consumer class to consume the message that was produced by sayHelloToRestPeople Method");
		log.debug(
				"This is a sample log message at debug level to check if the log contains the traceId and spanId or not");
		return "Hello from Producer ! You used Rest Template Communication.";
	}

	@GetMapping("/hello/fromWebClient")
	public String sayHelloUsingWebClient() {
		log.info(
				"At this point we have received a call from the consumer class to consume the message that was produced by sayHelloUsingWebClient Method");
		log.debug(
				"This is a sample log message at debug level to check if the log contains the traceId and spanId or not");

		Map<String, Object> headerMap = new HashMap<>();
		Span currentSpan = Span.current();
		if (currentSpan == null) {
			currentSpan = tracer.spanBuilder("WebSocketController").startSpan();
		}
		String traceId = currentSpan.getSpanContext().getTraceId();
		String spanId = currentSpan.getSpanContext().getSpanId();
		headerMap.put("traceId", traceId);
		headerMap.put("spanId", spanId);
		MessageHeaders messageHeaders = new MessageHeaders(headerMap);
		String topicFull = "/topic/messages";
		log.debug("Sending to topic:[{}] the message:[{}]", topicFull, "defaultTest");
		this.simpMessagingTemplate.convertAndSend(topicFull, "defaultTest", messageHeaders);
		return "Hello from Producer ! You used Web Client Communication";
	}

	@MessageMapping("/send")
	@SendTo("/topic/messages")
	public String sendMessage(String message) {
		return message;
	}
}
