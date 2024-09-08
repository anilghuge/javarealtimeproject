package com.example.model;

import java.time.LocalDate;

import lombok.Data;


@Data
public class ElibilityDetails {
	private Integer caseNo;
	private String holderName;
	private Long holderSSN;
	private String planName;
	private String planStatus;
	private LocalDate planStartDate;
	private LocalDate planEndDate;
	private Double benifitAmt;
	private String denialReason;
	private String bankName;
	private Long accountNumber;
}
