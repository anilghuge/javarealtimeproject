package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.COTriggersEntity;

public interface ICOTriggerRepository extends JpaRepository<COTriggersEntity,Integer> {

}
