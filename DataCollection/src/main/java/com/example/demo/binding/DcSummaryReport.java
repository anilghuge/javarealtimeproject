package com.example.demo.binding;

import java.util.List;

import lombok.Data;

@Data
public class DcSummaryReport {
	//private PlanSectionInputs planSectionDetails;
	private EductionInputs eductionDetails;
	private List<ChildInputs> childDetails;
	private IncomeInputs incomeDetails;
	private CitizenAppRegistrationInputs citizenDetails;
	private String planName;
}
