package com.microservice.consumer.Consumer.Controllercom.microservice.consumer.Consumer.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

import io.opentelemetry.api.trace.Span;

public class CustomLogger {

	private final Logger logger;

	@Value("${spring.application.name}")
	public String applicationName;

	// Constructor to initialize with a logger from LoggerFactory
	public CustomLogger(Class<?> clazz) {
		this.logger = LoggerFactory.getLogger(clazz);
	}

	// Support for Object... args with formatted messages
	public void info(String message, Object... args) {
		String traceId = Span.current().getSpanContext().getTraceId();
		String spanId = Span.current().getSpanContext().getSpanId();
		logger.info("[{},{},{},{}]", applicationName, traceId, spanId, message, args);
	}

	public void debug(String message, Object... args) {
		String traceId = Span.current().getSpanContext().getTraceId();
		String spanId = Span.current().getSpanContext().getSpanId();
		logger.debug("[{},{},{},{}]", applicationName, traceId, spanId, message, args);
	}
}
