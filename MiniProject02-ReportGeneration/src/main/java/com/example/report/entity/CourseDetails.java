package com.example.report.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="tbl_mst_course_details")
public class CourseDetails extends BaseParams{

	@Id
	private Integer courseId;
	private String courseName;
	private String location;
	private String courseCategory;
	private String facultyName;
	private Double fee;
	private String adminName;
	private String adminContact;
	private String trainingMode;
	private LocalDateTime startDate;
	
	
}
