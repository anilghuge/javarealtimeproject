package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.CitizenApplicationRegistrationEntity;

public interface IApplicationRegistrationRepository extends JpaRepository<CitizenApplicationRegistrationEntity, Integer> {

}
