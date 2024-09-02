package com.example.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.listeners.MessageStore;
import com.example.producer.MessageProducer;

@RestController
@RequestMapping("/kafka-api")
public class KafkaOperationController {

	@Autowired
	private MessageProducer producer;
	
	@Autowired
	private MessageStore messageStore;

	@GetMapping("/send")
	public ResponseEntity<String> sendMessage(@RequestParam String message) {
		String msg = producer.sendMessage(message);
		return new ResponseEntity<String>(msg, HttpStatus.OK);
	}
	
	@GetMapping("/read")
	public ResponseEntity<List<String>> sendMessage() {
		List<String> showAllMessage = messageStore.showAllMessage();
		return new ResponseEntity<>(showAllMessage, HttpStatus.OK);
	}
	
	
}
