package com.microservice.consumer.Consumer.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import io.opentelemetry.api.GlobalOpenTelemetry;
import io.opentelemetry.api.trace.Tracer;

@Configuration
public class RestTemplateConfig {

	@Bean
	RestTemplate restTemplate() {
		return new RestTemplate();
	}

	@Bean
	Tracer tracer() {
		return GlobalOpenTelemetry.getTracer("RestTemplateConfig");
	}

}
