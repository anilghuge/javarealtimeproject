package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.binding.CoSummary;
import com.example.demo.service.ICorrespodenceMgmtService;

@RestController
@RequestMapping("/co-triggers-api")
public class CoTriggerOperationController {

	@Autowired
	private ICorrespodenceMgmtService correspodenceMgmtService;
	
	@GetMapping("/process")
	public ResponseEntity<CoSummary> processAndUpdateTriggers()throws Exception{
		return new ResponseEntity<CoSummary>(correspodenceMgmtService.processPendingTriggers(),HttpStatus.OK);
	}
}
