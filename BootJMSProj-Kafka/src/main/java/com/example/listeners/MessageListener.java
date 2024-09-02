package com.example.listeners;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class MessageListener {

	@Autowired
	private MessageStore store;
	
	@KafkaListener(topics = "${topic.name}",groupId = "grp1")
	public void readMessage(String message) {
		store.addMessage(message);
	}
}
