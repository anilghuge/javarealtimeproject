package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.ElibilityDetailsEntity;

public interface IEligiblityDetermineRepository extends JpaRepository<ElibilityDetailsEntity,Integer> {

}
