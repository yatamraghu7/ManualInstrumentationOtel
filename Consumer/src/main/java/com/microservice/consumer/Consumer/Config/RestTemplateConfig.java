package com.microservice.consumer.Consumer.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import io.opentelemetry.api.OpenTelemetry;
import io.opentelemetry.instrumentation.spring.web.v3_1.SpringWebTelemetry;

@Configuration
public class RestTemplateConfig {

	@Bean
    RestTemplate restTemplate(OpenTelemetry openTelemetry) {
		RestTemplate restTemplate = new RestTemplate();
		SpringWebTelemetry telemetry = SpringWebTelemetry.create(openTelemetry);
		restTemplate.getInterceptors().add(telemetry.newInterceptor());
		return restTemplate;
	}

}
