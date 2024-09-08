package com.example.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.entity.ElibilityDetailsEntity;

public interface IEligibilityDeterminationRepository extends JpaRepository<ElibilityDetailsEntity,Integer> {

}
