package com.example.report.service;

import java.util.List;
import java.util.Set;

import com.example.report.vo.SearchInput;
import com.example.report.vo.SearchResult;

import jakarta.servlet.http.HttpServletResponse;

public interface ICourseMgmtService {

	Set<String> showAllCourseCategories();
	
	Set<String> showAllTrainingMode();
	
	Set<String> showAllFaculties();
	
	List<SearchResult> showCoursesByFilters(SearchInput input);
	
	void generatePdfReport(SearchInput input,HttpServletResponse response);
	
	void generateExcelReport(SearchInput input,HttpServletResponse response);
}
