package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.example.demo.entity.CitizenApplicationRegistrationEntity;
import com.example.demo.entity.DCCaseEntity;
import com.example.demo.entity.PlanEntity;
import com.example.demo.repository.IApplicationRegistrationRepository;
import com.example.demo.repository.IDCCaseRepository;
import com.example.demo.repository.IDCChildernRepository;
import com.example.demo.repository.IDCEductionRepository;
import com.example.demo.repository.IDCIncomeRepository;
import com.example.demo.repository.IPlanRepository;
import com.example.demo.service.impl.DcMgmtServiceImpl;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class DcMgmtServiceImplTest {

	@MockBean
	private IDCCaseRepository caseRepository;
	
	@MockBean
	private IApplicationRegistrationRepository citizenAppRepo;
	
	@MockBean
	private IPlanRepository planRepository;
	
	@MockBean
	private IDCIncomeRepository incomeRepository;
	
	@MockBean
	private IDCEductionRepository eductionRepository;
	
	@MockBean
	private IDCChildernRepository childernRepository;
	
	@InjectMocks
	private  DcMgmtServiceImpl dcMgmtService;
	
	@Test
	void generateCaseNoTest1() throws Exception {
		
		CitizenApplicationRegistrationEntity citizenAppEntity=new CitizenApplicationRegistrationEntity();
		citizenAppEntity.setAppId(1);
		Optional<CitizenApplicationRegistrationEntity> optCitizenEntity=Optional.of(citizenAppEntity);
		Mockito.when(citizenAppRepo.findById(1)).thenReturn(optCitizenEntity);
		
		DCCaseEntity caseEntity=new DCCaseEntity();
		caseEntity.setAppId(1);
		
		DCCaseEntity caseEntity1=new DCCaseEntity();
		caseEntity1.setAppId(1);
		caseEntity1.setCaseNo(1001);
		Mockito.when(caseRepository.save(caseEntity)).thenReturn(caseEntity1);
		
		int actual=dcMgmtService.generateCaseNumber(1);
		assertEquals(1001, actual);
	}
	
	@Test
	void generateCaseNoTest2() throws Exception {
		
		CitizenApplicationRegistrationEntity citizenAppEntity=new CitizenApplicationRegistrationEntity();
		citizenAppEntity.setAppId(1);
		Optional<CitizenApplicationRegistrationEntity> optCitizenEntity=Optional.of(citizenAppEntity);
		Mockito.when(citizenAppRepo.findById(1)).thenReturn(optCitizenEntity);
		
		DCCaseEntity caseEntity=new DCCaseEntity();
		caseEntity.setAppId(1);
		
		DCCaseEntity caseEntity1=new DCCaseEntity();
		caseEntity1.setAppId(1);
		caseEntity1.setCaseNo(1001);
		Mockito.when(caseRepository.save(caseEntity)).thenReturn(caseEntity1);
		
		int actual=dcMgmtService.generateCaseNumber(10);
		assertEquals(0, actual);
	}
	
	@Test
	public void showAllPlanNamesTest() {
		
		PlanEntity planEntity1=new PlanEntity();
		planEntity1.setPlanName("SNAP");
		PlanEntity planEntity2=new PlanEntity();
		planEntity2.setPlanName("MEDAID");
		PlanEntity planEntity3=new PlanEntity();
		planEntity3.setPlanName("MEDCARE");
		PlanEntity planEntity4=new PlanEntity();
		planEntity4.setPlanName("QHP");
		PlanEntity planEntity5=new PlanEntity();
		planEntity5.setPlanName("CCAP");
		PlanEntity planEntity6=new PlanEntity();
		planEntity6.setPlanName("CAJW");
		
		List<PlanEntity> planList=List.of(planEntity1,planEntity2,planEntity3,planEntity4,planEntity5,planEntity6);
		
		Mockito.when(planRepository.findAll()).thenReturn(planList);
		List<String> plansList = dcMgmtService.showallPlanNames();
		
		assertEquals(6, plansList.size());
		
	}
	
}
