package com.example.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.entity.CitizenApplicationRegistrationEntity;

public interface IApplicationRegistrationRepository extends JpaRepository<CitizenApplicationRegistrationEntity, Integer> {

}
