package com.example.demo.service;

import java.util.List;

import com.example.demo.binding.ChildInputs;
import com.example.demo.binding.DcSummaryReport;
import com.example.demo.binding.EductionInputs;
import com.example.demo.binding.IncomeInputs;
import com.example.demo.binding.PlanSelectionInputs;

public interface IDcMgmtService {
	public Integer generateCaseNumber(Integer appId);
	public List<String> showallPlanNames();
	public Integer savePlanSelection(PlanSelectionInputs plan);
	public Integer saveIncomeDetails(IncomeInputs income);
	public Integer saveEductionDetails(EductionInputs eduction);
	public Integer saveChildernDetails(List<ChildInputs> childern);
	public DcSummaryReport showDcSummary(Integer caseNo);
}
