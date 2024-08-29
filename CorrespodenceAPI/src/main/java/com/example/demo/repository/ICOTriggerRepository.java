package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.COTriggersEntity;
import java.util.List;

public interface ICOTriggerRepository extends JpaRepository<COTriggersEntity, Integer> {

	List<COTriggersEntity> findByTriggerStatus(String triggerStatus);

	COTriggersEntity findByCaseNo(Integer caseNo);
}
