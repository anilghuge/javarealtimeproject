package com.example.user.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.user.entity.UserMaster;


public interface IUserMasterDAO extends JpaRepository<UserMaster, Integer> {

	UserMaster findByEmailAndPassword(String email, String password);

	UserMaster findByEmailAndName(String email, String name);
}
