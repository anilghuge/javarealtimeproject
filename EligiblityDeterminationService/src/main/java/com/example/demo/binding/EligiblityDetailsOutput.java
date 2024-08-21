package com.example.demo.binding;

import java.time.LocalDate;

import lombok.Data;

@Data
public class EligiblityDetailsOutput {

	private String holderName;
	private Long holderSSN;
	private String planName;
	private String planStatus;
	private LocalDate planStartDate;
	private LocalDate planEndDate;
	private Double benifitAmt;
	private String denialReason;
}
