package com.example.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customer-api")
public class CustomerOperationController {

	@GetMapping("/report")
	public ResponseEntity<String> showReport(){
		return new ResponseEntity<String>("Customer Report",HttpStatus.OK);
	}
}
