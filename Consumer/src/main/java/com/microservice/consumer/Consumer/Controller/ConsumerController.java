package com.microservice.consumer.Consumer.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

@RestController
public class ConsumerController {

	protected final RestTemplate restTemplate;
	
    protected final WebClient webClient;

	public ConsumerController(WebClient.Builder webClientBuilder, RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
        this.webClient = webClientBuilder.baseUrl("http://localhost:8081").build();
	}
	
	@GetMapping("/consume")
	public String consumeHello() {
		String response = restTemplate.getForObject("http://localhost:8081/hello/fromRestTemplate", String.class);
		System.out.println("Sent request to producer and got respomse as :" + response);
		return "Consumer received: " + response;
	}
	
	 @GetMapping("/consume/usingWebClient")
	    public String consume() {
	        return this.webClient.get()
	                .uri("/hello/fromWebClient")
	                .retrieve()
	                .bodyToMono(String.class)
	                .block();
	    }

}
