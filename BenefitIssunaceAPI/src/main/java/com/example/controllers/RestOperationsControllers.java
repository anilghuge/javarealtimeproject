package com.example.controllers;

import org.springframework.batch.core.JobExecution;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.service.IBenifiitIssuanceMgmtService;

@RestController
@RequestMapping("/bi-api")
public class RestOperationsControllers {

	@Autowired
	private IBenifiitIssuanceMgmtService benifiitIssuanceMgmtService;
	
	@GetMapping("/send")
	public ResponseEntity<String> sendAmount() throws Exception{
		JobExecution execution = benifiitIssuanceMgmtService.sendAmountToBenificries();
		return new ResponseEntity<String>(execution.getExitStatus().getExitDescription(),HttpStatus.OK);
	}
}
