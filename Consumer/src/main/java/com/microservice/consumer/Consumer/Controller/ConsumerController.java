package com.microservice.consumer.Consumer.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class ConsumerController {

	private final RestTemplate restTemplate;

	public ConsumerController(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}

	@GetMapping("/consume")
	public String consumeHello() {
		String response = restTemplate.getForObject("http://localhost:8081/hello", String.class);
		return "Consumer received: " + response;
	}

}
