package com.example.demo.service.impl;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.binding.EligibilityDetailsOutput;
import com.example.demo.entity.DCCaseEntity;
import com.example.demo.entity.DCChildernEntity;
import com.example.demo.entity.DCIncomeEntity;
import com.example.demo.entity.PlanEntity;
import com.example.demo.repository.IDCCaseRepository;
import com.example.demo.repository.IDCChildernRepository;
import com.example.demo.repository.IDCIncomeRepository;
import com.example.demo.repository.IPlanRepository;
import com.example.demo.service.IEligiblityDeterminationMgmtService;

@Service
public class EligiblityDeterminationMgmtServiceImpl implements IEligiblityDeterminationMgmtService {

	@Autowired
	private IDCCaseRepository caseRepo;

	@Autowired
	private IPlanRepository planRepository;

	@Autowired
	private IDCIncomeRepository incomeRepository;

	@Autowired
	private IDCChildernRepository childernRepository;

	@Override
	public EligibilityDetailsOutput determineEligiblity(Integer caseNo) {
		// get planId and appId based on caseNo
		Integer appId = null;
		Integer planId = null;
		Optional<DCCaseEntity> optCaseEntity = caseRepo.findById(caseNo);
		if (optCaseEntity.isPresent()) {
			DCCaseEntity caseEntity = optCaseEntity.get();
			planId = caseEntity.getPlanId();
			appId = caseEntity.getAppId();
		}

		// get plan Name
		String planName = null;
		Optional<PlanEntity> optPlanEntity = planRepository.findById(planId);
		if (optPlanEntity.isPresent()) {
			PlanEntity planEntity = optPlanEntity.get();
			planName = planEntity.getPlanName();
		}

		// call helper method to plan conditions
		EligibilityDetailsOutput eligiblityDetailsOutput = applyPlanCondition(caseNo, planName);

		// save Eligibility entity object
		// save CoTriggers Object
		return eligiblityDetailsOutput;
	}

	private EligibilityDetailsOutput applyPlanCondition(Integer caseNo, String planName) {
		EligibilityDetailsOutput eligiblityDetailsOutput = new EligibilityDetailsOutput();
		eligiblityDetailsOutput.setPlanName(planName);

		// get income details of the citizen

		DCIncomeEntity incomeEntity = incomeRepository.findByCaseNo(caseNo);
		Double employeeIncome = incomeEntity.getEmployeeIncome();
		Double propertyIncome = incomeEntity.getPropertyIncome();

		// for SNAP
		if (planName.equalsIgnoreCase("SNAP")) {
			if (employeeIncome <= 300) {
				eligiblityDetailsOutput.setPlanStatus("Approved");
				eligiblityDetailsOutput.setBenifitAmt(200.0);
			} else {
				eligiblityDetailsOutput.setPlanStatus("Denied");
				eligiblityDetailsOutput.setDenialReason("High Income");
			}
		} else if (planName.equalsIgnoreCase("CCAP")) {
			boolean kidsCountCondition = false;
			boolean kidsAgeCondition = true;

			List<DCChildernEntity> listChilds = childernRepository.findByCaseNo(caseNo);
			if (!listChilds.isEmpty()) {
				kidsCountCondition = true;

				for (DCChildernEntity child : listChilds) {
					int kidAge = Period.between(child.getChildDOB(), LocalDate.now()).getYears();
					if (kidAge > 16) {
						kidsAgeCondition = false;
						break;
					}//if
				}//for
			}//if

			if(employeeIncome<=300 && kidsCountCondition && kidsAgeCondition) {
				eligiblityDetailsOutput.setPlanStatus("Approved");
				eligiblityDetailsOutput.setBenifitAmt(300.0);
			}else {
				eligiblityDetailsOutput.setPlanStatus("Denied");
				eligiblityDetailsOutput.setDenialReason("CCAP rules are not satisfied");
			}
			
		} else if (planName.equalsIgnoreCase("MEDAID")) {
			if(employeeIncome<=300 && propertyIncome==0) {
				eligiblityDetailsOutput.setPlanStatus("Approved");
				eligiblityDetailsOutput.setBenifitAmt(300.0);
			}else {
				eligiblityDetailsOutput.setPlanStatus("Denied");
				eligiblityDetailsOutput.setDenialReason("MEDAID rules are not satisfied");
			}
		} else if (planName.equalsIgnoreCase("MEDCARE")) {
			
		} else if (planName.equalsIgnoreCase("CAJW")) {

		} else {

		}

		// set the common properties for eligibility object
		if (eligiblityDetailsOutput.getPlanStatus().equalsIgnoreCase("Approved")) {
			eligiblityDetailsOutput.setPlanStartDate(LocalDate.now());
			eligiblityDetailsOutput.setPlanEndDate(LocalDate.now().plusYears(2));
		}
		return eligiblityDetailsOutput;
	}

}
