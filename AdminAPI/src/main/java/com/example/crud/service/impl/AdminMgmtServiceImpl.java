package com.example.crud.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.crud.binding.PlanData;
import com.example.crud.dao.IPlanCategoryDAO;
import com.example.crud.dao.IPlanDAO;
import com.example.crud.entity.PlanCategory;
import com.example.crud.entity.PlanEntity;
import com.example.crud.service.IAdminMgmtService;

@Service
public class AdminMgmtServiceImpl implements IAdminMgmtService {

	@Autowired
	private IPlanDAO planDAO;

	@Autowired
	private IPlanCategoryDAO planCategoryDAO;

	@Override
	public String registerPlan(PlanData plan) {
		// convert PlanData binding object to Plan entity
		PlanEntity entity = new PlanEntity();
		BeanUtils.copyProperties(plan, entity);
		PlanEntity save = planDAO.save(entity);
		return "Travel plan is saved with id" + save.getPlanId();
	}

	@Override
	public Map<Integer, String> getPlanCategories() {
		List<PlanCategory> list = planCategoryDAO.findAll();
		Map<Integer, String> categories = new HashMap<Integer, String>();
		list.forEach(l -> {
			categories.put(l.getCategoryId(), l.getCategoryName());
		});
		return categories;
	}

	@Override
	public List<PlanData> showAllPlans() {
		List<PlanEntity> listEntities = planDAO.findAll();
		List<PlanData> listPlanData = new ArrayList<>();
		listEntities.forEach(entity -> {
			PlanData plan = new PlanData();
			BeanUtils.copyProperties(entity, plan);
			listPlanData.add(plan);
		});
		return listPlanData;
	}

	@Override
	public PlanData showPlanById(Integer planId) {
		PlanEntity planEntity = planDAO.findById(planId)
				.orElseThrow(() -> new IllegalArgumentException("Travelplan is not found"));
		PlanData planData = new PlanData();
		BeanUtils.copyProperties(planEntity, planData);
		return planData;
	}

	@Override
	public String updatePlan(PlanData plan) {
		Optional<PlanEntity> optional = planDAO.findById(plan.getPlanId());
		if (optional.isPresent()) {
			PlanEntity entity = optional.get();
			BeanUtils.copyProperties(plan, entity);
			planDAO.save(entity);
			return plan.getPlanId() + "Travel Plan is Updated";
		} else {
			return plan.getPlanId() + "Travel Plan is not Updated";
		}
	}

	@Override
	public String deletePlan(Integer planId) {
		Optional<PlanEntity> optional = planDAO.findById(planId);
		if (optional.isPresent()) {
			planDAO.deleteById(planId);
			return planId + "Travel Plan is Deleted";
		} else {
			return planId + "Travel Plan is not Deleted";
		}
	}

	@Override
	public String changePlanStatus(Integer planId, String status) {
		Optional<PlanEntity> optional = planDAO.findById(planId);
		if (optional.isPresent()) {
			PlanEntity plan = optional.get();
			plan.setActiveSw(status);
			planDAO.save(plan);
			return planId + "Travel Plan status is changed";
		} else {
			return planId + "Travel Plan is not changed";
		}
	}

}
