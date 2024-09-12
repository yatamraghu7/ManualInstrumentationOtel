package com.microservice.producer.Producer.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProducerController {

	@GetMapping("/hello")
	public String sayHello() {
		return "Hello from Producer!";
	}

}
