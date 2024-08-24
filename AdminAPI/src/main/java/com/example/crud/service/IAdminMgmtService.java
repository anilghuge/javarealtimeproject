package com.example.crud.service;

import java.util.List;
import java.util.Map;

import com.example.crud.binding.PlanData;

public interface IAdminMgmtService {
	String registerPlan(PlanData plan);
	Map<Integer, String> getPlanCategories();
	List<PlanData> showAllPlans();
	PlanData showPlanById(Integer planId);
	String updatePlan(PlanData plan);
	String deletePlan(Integer planid);
	String changePlanStatus(Integer planId, String status);
}
