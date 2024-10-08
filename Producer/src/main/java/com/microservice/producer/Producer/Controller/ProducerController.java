package com.microservice.producer.Producer.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.microservice.producer.Producer.Utils.CustomLogger;

@RestController
public class ProducerController {

	private final CustomLogger log = new CustomLogger(ProducerController.class);

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
		return "Hello from Producer ! You used Web Client Communication";
	}
}
