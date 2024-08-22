package com.example.demo.service;

import com.example.demo.binding.EligibilityDetailsOutput;

public interface IEligiblityDeterminationMgmtService {

	public EligibilityDetailsOutput determineEligiblity(Integer caseNo);
}
