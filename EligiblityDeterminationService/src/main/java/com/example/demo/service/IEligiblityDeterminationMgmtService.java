package com.example.demo.service;

import com.example.demo.binding.EligiblityDetailsOutput;

public interface IEligiblityDeterminationMgmtService {

	public EligiblityDetailsOutput determineEligiblity(Integer caseNo);
}
