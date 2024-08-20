package com.example.demo.binding;

import java.time.LocalDate;

import lombok.Data;
@Data
public class ChildInputs {
	private Integer childId;
	private LocalDate childDOB;
	private Long childSSN;
	private Integer caseNo;
}
