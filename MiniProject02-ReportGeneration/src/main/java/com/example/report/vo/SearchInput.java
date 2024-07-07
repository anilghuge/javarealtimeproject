package com.example.report.vo;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SearchInput {
	private String courseCategory;
	private String trainingMode;
	private String facultyName;
	private LocalDateTime startOn;
}
