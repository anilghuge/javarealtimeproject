package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.ElibilityDetailsEntity;

public interface IEligibilityDeterminationRepository extends JpaRepository<ElibilityDetailsEntity, Integer> {

	public ElibilityDetailsEntity findByCaseNo(Integer caseNo);
}
