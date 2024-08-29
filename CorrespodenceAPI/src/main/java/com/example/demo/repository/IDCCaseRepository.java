package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.DCCaseEntity;

public interface IDCCaseRepository extends JpaRepository<DCCaseEntity, Integer> {

}
