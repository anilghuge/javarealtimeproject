package com.example.crud.service;

import java.util.List;
import java.util.Map;

import com.example.crud.entity.TravelPlan;

public interface ITravelPlanMgmtService {

	String registerTravelPlan(TravelPlan plan);

	Map<Integer, String> getTravelPlanCategories();

	List<TravelPlan> showAllTravelPlans();

	TravelPlan showTravelPlanById(Integer planId);

	String updateTravlePlan(TravelPlan plan);

	String deleteTravlePlan(Integer planid);

	String changeTravlePlan(Integer planId, String status);
}
