package com.example.crud.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.crud.binding.PlanData;
import com.example.crud.service.IAdminMgmtService;

@RestController
@RequestMapping("/plan-api")
public class AdminOperationsController {

	@Autowired
	private IAdminMgmtService planMgmtService;

	@GetMapping("/categories")
	public ResponseEntity<Map<Integer, String>> getPlanCategories() {
		Map<Integer, String> categories = planMgmtService.getPlanCategories();
		return new ResponseEntity<>(categories, HttpStatus.OK);

	}

	@PostMapping("/register")
	public ResponseEntity<String> registerPlan(@RequestBody PlanData plan) {

		String response = planMgmtService.registerPlan(plan);
		return new ResponseEntity<>(response, HttpStatus.CREATED);

	}

	@GetMapping("/all")
	public ResponseEntity<List<PlanData>> showAllPlans() {
		List<PlanData> plans = planMgmtService.showAllPlans();
		return new ResponseEntity<>(plans, HttpStatus.OK);
	}

	@GetMapping("/show/{id}")
	public ResponseEntity<PlanData> showPlanById(@PathVariable("id") Integer planId) {

		PlanData plan = planMgmtService.showPlanById(planId);
		return new ResponseEntity<>(plan, HttpStatus.OK);
	}

	@PutMapping("/update")
	public ResponseEntity<String> updatePlan(@RequestBody PlanData plan) {
		String response = planMgmtService.updatePlan(plan);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deletePlan(@PathVariable("id") Integer planId) {
		String response = planMgmtService.deletePlan(planId);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@PatchMapping("/changeStatus/{id}/{status}")
	public ResponseEntity<String> changePlanStatus(@PathVariable("id") Integer planId, @PathVariable String status) {
		String response = planMgmtService.changePlanStatus(planId, status);
		return new ResponseEntity<>(response, HttpStatus.OK);

	}
}
