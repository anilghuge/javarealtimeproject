package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.binding.EligibilityDetailsOutput;
import com.example.demo.service.IEligiblityDeterminationMgmtService;

@RestController
@RequestMapping("/ed-api")
public class EligibilityDeterminationOperationController {

	@Autowired
	private IEligiblityDeterminationMgmtService eligiblityDeterminationMgmtService;
	
	@GetMapping("/determine/{caseNo}")
	public ResponseEntity<EligibilityDetailsOutput> checkPlanElgibility(@PathVariable Integer caseNo){
		EligibilityDetailsOutput output=eligiblityDeterminationMgmtService.determineEligiblity(caseNo);
		return new ResponseEntity<>(output,HttpStatus.CREATED);
	}
	
}
