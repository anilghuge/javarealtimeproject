package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.DCChildernEntity;
import com.example.demo.entity.DCEductionEntity;

public interface IDCEductionRepository extends JpaRepository<DCEductionEntity, Integer> {

	DCEductionEntity findByCaseNo(Integer caseNo);
}
