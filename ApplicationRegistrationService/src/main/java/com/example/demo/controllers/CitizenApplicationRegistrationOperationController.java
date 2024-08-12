package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.binding.CitizenAppRegistrationInputs;
import com.example.demo.service.ICitizenApplicationRegistratipnService;

@RestController
@RequestMapping("/CitizenAR-api")
public class CitizenApplicationRegistrationOperationController {

	@Autowired
	private ICitizenApplicationRegistratipnService registratipnService;

	@PostMapping("/save")
	public ResponseEntity<String> saveCitizenApplication(@RequestBody CitizenAppRegistrationInputs input) {
		try {
			Integer applicationId = registratipnService.registerCitizenApplication(input);

			if (applicationId > 0)
				return new ResponseEntity<String>("Citizen Application is Registrated with id " + applicationId,
						HttpStatus.CREATED);
			else
				return new ResponseEntity<String>(" Invalid SSN or Citizen must beling to California state",
						HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}

	}

}
