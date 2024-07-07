package com.example.crud.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.crud.dao.IPlanCategoryDAO;
import com.example.crud.dao.ITravelPlanDAO;
import com.example.crud.entity.PlanCategory;
import com.example.crud.entity.TravelPlan;
import com.example.crud.service.ITravelPlanMgmtService;

@Service
public class TravelPlanMgmtServiceImpl implements ITravelPlanMgmtService {

	@Autowired
	private ITravelPlanDAO travelPlanDAO;
	
	@Autowired
	private IPlanCategoryDAO planCategoryDAO;
	
	@Override
	public String registerTravelPlan(TravelPlan plan) {
		TravelPlan save = travelPlanDAO.save(plan);
		return "Travel plan is saved with id"+save.getPlanId();
	}

	@Override
	public Map<Integer, String> getTravelPlanCategories() {
		List<PlanCategory> list = planCategoryDAO.findAll();
		Map<Integer, String> categories=new HashMap<Integer, String>();
		list.forEach(l->{
			categories.put(l.getCategoryId(),l.getCategoryName());
		});
		return categories;
	}

	@Override
	public List<TravelPlan> showAllTravelPlans() {
		return travelPlanDAO.findAll();
	}

	@Override
	public TravelPlan showTravelPlanById(Integer planId) {
		return travelPlanDAO.findById(planId).orElseThrow(()->new IllegalArgumentException("Travelplan is not found"));
	}

	@Override
	public String updateTravlePlan(TravelPlan plan) {
		Optional<TravelPlan> optional = travelPlanDAO.findById(plan.getPlanId());
		if(optional.isPresent()) {
			travelPlanDAO.save(plan);
			return plan.getPlanId() +"Travel Plan is Updated";
		}else {
			return plan.getPlanId() +"Travel Plan is not Updated";
		}
	}

	@Override
	public String deleteTravlePlan(Integer planId) {
		Optional<TravelPlan> optional = travelPlanDAO.findById(planId);
		if(optional.isPresent()) {
			travelPlanDAO.deleteById(planId);
			return planId +"Travel Plan is Deleted";
		}else {
			return planId+"Travel Plan is not Deleted";
		}
	}

	@Override
	public String changeTravlePlan(Integer planId, String status) {
		Optional<TravelPlan> optional = travelPlanDAO.findById(planId);
		if(optional.isPresent()) {
			TravelPlan travelPlan = optional.get();
			travelPlan.setActive(status);
			travelPlanDAO.save(travelPlan);
			return planId +"Travel Plan status is changed";
		}else {
			return planId+"Travel Plan is not changed";
		}
	}

}
