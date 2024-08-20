package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.DCChildernEntity;

public interface IDCChildernRepository extends JpaRepository<DCChildernEntity, Integer> {

	List<DCChildernEntity> findByCaseNo(Integer caseNo);
}
