package com.example.report.vo;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SearchResult {

	private Integer courseId;
	private String courseName;
	private String location;
	private String courseCategory;
	private String facultyName;
	private Double fee;
	private String adminContact;
	private String trainingMode;
	private LocalDateTime startDate;
}
