package com.example.report.dao;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.report.entity.CourseDetails;

public interface ICourseDetailsDAO extends JpaRepository<CourseDetails, Integer> {

	@Query("Select distinct(courseCategory) from CourseDetails")
	Set<String> getUniqueCourseCategories();
	
	@Query("Select distinct(facultyName) from CourseDetails")
	Set<String> getUniqueFacultyNames();
	
	@Query("Select distinct(trainingMode) from CourseDetails")
	Set<String> getUniqueTrainingMode();
	
	
}
