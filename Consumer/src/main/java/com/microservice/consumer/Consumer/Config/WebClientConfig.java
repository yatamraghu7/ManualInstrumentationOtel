package com.microservice.consumer.Consumer.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.server.WebFilter;

import io.opentelemetry.api.OpenTelemetry;
import io.opentelemetry.instrumentation.spring.webflux.v5_3.SpringWebfluxTelemetry;

@Configuration
public class WebClientConfig {

	private final SpringWebfluxTelemetry webfluxTelemetry;

	public WebClientConfig(OpenTelemetry openTelemetry) {
		this.webfluxTelemetry = SpringWebfluxTelemetry.builder(openTelemetry).build();
	}

	// Adds instrumentation to WebClients
	@Bean
	WebClient.Builder webClient() {
		WebClient webClient = WebClient.create();
		return webClient.mutate().filters(webfluxTelemetry::addClientTracingFilter);
	}

	// Adds instrumentation to Webflux server
	@Bean
	WebFilter webFilter() {
		return webfluxTelemetry.createWebFilterAndRegisterReactorHook();
	}
}
