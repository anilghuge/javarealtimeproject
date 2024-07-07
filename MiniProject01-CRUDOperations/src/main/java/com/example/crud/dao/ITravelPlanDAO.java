package com.example.crud.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.crud.entity.TravelPlan;

public interface ITravelPlanDAO extends JpaRepository<TravelPlan,Integer> {
	
}
