package com.example.crud.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.crud.entity.PlanEntity;

public interface IPlanDAO extends JpaRepository<PlanEntity,Integer> {
	
}
