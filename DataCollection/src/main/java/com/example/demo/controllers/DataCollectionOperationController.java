package com.example.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.binding.ChildInputs;
import com.example.demo.binding.DcSummaryReport;
import com.example.demo.binding.EductionInputs;
import com.example.demo.binding.IncomeInputs;
import com.example.demo.binding.PlanSelectionInputs;
import com.example.demo.service.IDcMgmtService;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/dc-api")
@Tag(name="dc-api",description="Data Collection Module microservice")
public class DataCollectionOperationController {

	@Autowired
	private IDcMgmtService dcService;

	@GetMapping("/planNames")
	public ResponseEntity<List<String>> displayPlanNames() {
		// use service
		List<String> showallPlanNames = dcService.showallPlanNames();
		return new ResponseEntity<>(showallPlanNames,HttpStatus.OK);
	}
	
	@PostMapping("/generateCaseNo/{appId}")
	public ResponseEntity<Integer> generateCaseNumber(@PathVariable Integer appId){
		// use service
		Integer caseNumber = dcService.generateCaseNumber(appId);
		return new ResponseEntity<>(caseNumber,HttpStatus.CREATED);
	}
	
	@PutMapping("/updatePlanSelection")
	public ResponseEntity<Integer> savePlanSection(@RequestBody PlanSelectionInputs inputs){
		Integer caseNumber = dcService.savePlanSelection(inputs);
		return new ResponseEntity<>(caseNumber,HttpStatus.OK);
	}
	
	@PostMapping("/saveIncome")
	public ResponseEntity<Integer> saveIncomeDetails(@RequestBody IncomeInputs income){
		Integer caseNumber=dcService.saveIncomeDetails(income);
		return new ResponseEntity<>(caseNumber,HttpStatus.CREATED);
	}
	
	@PostMapping("/saveEduction")
	public ResponseEntity<Integer> saveEductionDetails(@RequestBody EductionInputs eduction){
		Integer caseNumber=dcService.saveEductionDetails(eduction);
		return new ResponseEntity<>(caseNumber,HttpStatus.CREATED);
	}
	
	@PostMapping("/saveChilds")
	public ResponseEntity<Integer> saveChilderDetails(@RequestBody List<ChildInputs> childInput){
		Integer caseNumber = dcService.saveChildernDetails(childInput);
		return new ResponseEntity<>(caseNumber,HttpStatus.CREATED);
	}
	
	@GetMapping("/summary/{caseNo}")
	public ResponseEntity<DcSummaryReport> showSummaryReport(@PathVariable Integer caseNo){
		DcSummaryReport showDcSummary = dcService.showDcSummary(caseNo);
		return new ResponseEntity<>(showDcSummary,HttpStatus.OK);
	}
}
