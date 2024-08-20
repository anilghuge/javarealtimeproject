package com.example.demo.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.binding.ChildInputs;
import com.example.demo.binding.CitizenAppRegistrationInputs;
import com.example.demo.binding.DcSummaryReport;
import com.example.demo.binding.EductionInputs;
import com.example.demo.binding.IncomeInputs;
import com.example.demo.binding.PlanSelectionInputs;
import com.example.demo.entity.CitizenApplicationRegistrationEntity;
import com.example.demo.entity.DCCaseEntity;
import com.example.demo.entity.DCChildernEntity;
import com.example.demo.entity.DCEductionEntity;
import com.example.demo.entity.DCIncomeEntity;
import com.example.demo.entity.PlanEntity;
import com.example.demo.repository.IApplicationRegistrationRepository;
import com.example.demo.repository.IDCCaseRepository;
import com.example.demo.repository.IDCChildernRepository;
import com.example.demo.repository.IDCEductionRepository;
import com.example.demo.repository.IDCIncomeRepository;
import com.example.demo.repository.IPlanRepository;
import com.example.demo.service.IDcMgmtService;

@Service
public class DcMgmtServiceImpl implements IDcMgmtService {

	@Autowired
	private IDCCaseRepository caseRepository;
	
	@Autowired
	private IApplicationRegistrationRepository citizenAppRepo;
	
	@Autowired
	private IPlanRepository planRepository;
	
	@Autowired
	private IDCIncomeRepository incomeRepository;
	
	@Autowired
	private IDCEductionRepository eductionRepository;
	
	@Autowired
	private IDCChildernRepository childernRepository;
	
	
	@Override
	public Integer generateCaseNumber(Integer appId) {
		
		Optional<CitizenApplicationRegistrationEntity> appCitizen=citizenAppRepo.findById(appId);
		if(appCitizen.isPresent()) {
			DCCaseEntity caseEntity=new DCCaseEntity();
			caseEntity.setAppId(appId);
			return caseRepository.save(caseEntity).getCaseNo();
		}
		return 0;
	}

	@Override
	public List<String> showallPlanNames() {
		List<PlanEntity> list=planRepository.findAll();
		return list.stream().map(l->l.getPlanName()).toList();
	}

	@Override
	public Integer savePlanSelection(PlanSelectionInputs plan) {
		//load DcCaseEntity object
		Optional<DCCaseEntity> opt=caseRepository.findById(plan.getCaseNo());
		if(opt.isPresent()) {
			DCCaseEntity caseEntity=opt.get();
			caseEntity.setPlanId(plan.getPlanId());
			//update the DcCaseEntity with plan id
			caseRepository.save(caseEntity);//update object operation
			return caseEntity.getCaseNo();
		}
		return 0;
	}

	@Override
	public Integer saveIncomeDetails(IncomeInputs income) {
		//Convert Binding object data to Entity Object Data
		DCIncomeEntity incomeEntity=new DCIncomeEntity();
		BeanUtils.copyProperties(income, incomeEntity);
		//save the income details
		DCIncomeEntity save = incomeRepository.save(incomeEntity);
		return save.getCaseNo();
	}

	@Override
	public Integer saveEductionDetails(EductionInputs eduction) {
		//Convert Binding Object to Entity Object
		DCEductionEntity eductionEntity=new DCEductionEntity();
		BeanUtils.copyProperties(eduction, eductionEntity);
		DCEductionEntity save = eductionRepository.save(eductionEntity);
		return save.getCaseNo();
	}

	@Override
	public Integer saveChildernDetails(List<ChildInputs> childern) {
		//Convert Each Binding class Object to each Entity class Object
		childern.forEach(child->{
			DCChildernEntity childernEntity=new DCChildernEntity();
			BeanUtils.copyProperties(child, childernEntity);
			childernRepository.save(childernEntity);
		});
		return childern.get(0).getCaseNo();
	}

	@Override
	public DcSummaryReport showDcSummary(Integer caseNo) {
		DcSummaryReport summaryReport=new DcSummaryReport();
		//get multiple entity objects based on caseNo
		DCIncomeEntity incomeEntity=incomeRepository.findByCaseNo(caseNo);
		DCEductionEntity eductionEntity = eductionRepository.findByCaseNo(caseNo);
		List<DCChildernEntity> childernEntities = childernRepository.findByCaseNo(caseNo);
		Optional<DCCaseEntity> optCaseEntity=caseRepository.findById(caseNo);
		String planName=null;
		Integer appId=null;
		Integer planId=null;
		if(optCaseEntity.isPresent()) {
			DCCaseEntity caseEntity=optCaseEntity.get();
			planId=caseEntity.getPlanId();
			appId=caseEntity.getAppId();
			Optional<PlanEntity> optPlanEntity = planRepository.findById(planId);
			if(optPlanEntity.isPresent()) {
				planName = optPlanEntity.get().getPlanName();
			}
		}
		//Convert Entity Object to Binding Object
		
		CitizenAppRegistrationInputs citizenAppRegistrationInputs=new CitizenAppRegistrationInputs();
		
		Optional<CitizenApplicationRegistrationEntity> optionalCitizen = citizenAppRepo.findById(appId);
		if(optionalCitizen.isPresent()) {
			BeanUtils.copyProperties(optionalCitizen.get(),citizenAppRegistrationInputs);
		}
		
		IncomeInputs incomeInputs=new IncomeInputs();
		BeanUtils.copyProperties(incomeEntity, incomeInputs);
		
		List<ChildInputs> childInputs=new ArrayList<>();
		
		childernEntities.forEach(child->{
			ChildInputs childInput=new ChildInputs();
			BeanUtils.copyProperties(child, childInput);
			childInputs.add(childInput);
		});
		
		EductionInputs eductionInput=new EductionInputs();
		BeanUtils.copyProperties(eductionEntity, eductionInput);
		
		summaryReport.setPlanName(planName);
		summaryReport.setChildDetails(childInputs);
		summaryReport.setCitizenDetails(citizenAppRegistrationInputs);
		summaryReport.setEductionDetails(eductionInput);
		summaryReport.setIncomeDetails(incomeInputs);
		
		return summaryReport;
	}

}
