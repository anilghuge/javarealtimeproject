package com.example.demo.service;

import com.example.demo.binding.CitizenAppRegistrationInputs;
import com.example.demo.exception.InvalidSSNException;

public interface ICitizenApplicationRegistratipnService {

	public Integer registerCitizenApplication(CitizenAppRegistrationInputs input)throws InvalidSSNException;
}
