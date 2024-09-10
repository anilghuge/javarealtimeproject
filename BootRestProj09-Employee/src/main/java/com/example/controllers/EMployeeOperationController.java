package com.example.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/employee-api")
public class EMployeeOperationController {

	@GetMapping("/report")
	public ResponseEntity<String> showReport(){
		return new ResponseEntity<String>("Employee Report",HttpStatus.OK);
	}
}
