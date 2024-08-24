package com.example.crud.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.crud.entity.PlanCategory;

public interface IPlanCategoryDAO extends JpaRepository<PlanCategory,Integer> {

}
