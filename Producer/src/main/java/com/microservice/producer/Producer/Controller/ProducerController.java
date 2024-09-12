package com.microservice.producer.Producer.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProducerController {

	@GetMapping("/hello")
	public String sayHello() {
		return "Hello from Producer!";
	}

	
	@GetMapping("/hello/fromRestTemplate")
	public String sayHelloToRestPeople() {
		return "Hello from Producer! You used Rest Template Communication.";
	}
	
	@GetMapping("/hello/fromWebClient")
	public String sayHelloUsingWebClient() {
		return "Hello from Producer!You used Web Client Communication";
	}
}
