package com.example.listeners;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class MessageStore {

	private List<String> msgList=new ArrayList<String>();
	
	
	public List<String> showAllMessage() {
		return msgList;
	}
	
	
	public void addMessage(String message) {
		 msgList.add(message);
	}
	
	
	
	
}
