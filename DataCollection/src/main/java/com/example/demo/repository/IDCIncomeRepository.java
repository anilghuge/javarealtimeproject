package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.DCIncomeEntity;
import java.util.List;


public interface IDCIncomeRepository extends JpaRepository<DCIncomeEntity, Integer> {

	DCIncomeEntity findByCaseNo(Integer caseNo);
	
}
