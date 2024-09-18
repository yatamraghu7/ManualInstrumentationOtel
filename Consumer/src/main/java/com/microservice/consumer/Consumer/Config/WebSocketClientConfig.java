package com.microservice.consumer.Consumer.Config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.converter.StringMessageConverter;
import org.springframework.messaging.simp.stomp.StompHeaders;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.messaging.simp.stomp.StompSessionHandlerAdapter;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.messaging.WebSocketStompClient;

@Configuration
public class WebSocketClientConfig {

	@Scheduled(fixedDelay = 5000)
	public void connectWebSocket() throws Exception {
		WebSocketStompClient stompClient = new WebSocketStompClient(new StandardWebSocketClient());
		stompClient.setMessageConverter(new StringMessageConverter());

		String url = "ws://localhost:8080/ws-producer";
		StompSession session = stompClient.connect(url, new StompSessionHandlerAdapter() {
		}).get();

		session.subscribe("/topic/messages", new StompSessionHandlerAdapter() {
			@Override
			public void handleFrame(StompHeaders headers, Object payload) {
				System.out.println("Received message: " + payload);
			}
		});
	}
}