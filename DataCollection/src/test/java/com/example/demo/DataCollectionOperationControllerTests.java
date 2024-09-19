package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.example.demo.binding.ChildInputs;
import com.example.demo.binding.DcSummaryReport;
import com.example.demo.binding.EductionInputs;
import com.example.demo.binding.IncomeInputs;
import com.example.demo.binding.PlanSelectionInputs;
import com.example.demo.controllers.DataCollectionOperationController;
import com.example.demo.service.IDcMgmtService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

@WebMvcTest(value = DataCollectionOperationController.class)
public class DataCollectionOperationControllerTests {

	@MockBean
	private IDcMgmtService dcMgmtService;

	@Autowired
	private MockMvc mockMvc;

	@Test
	void displayPlanNamesTest() throws Exception {
		List<String> planList = new ArrayList<>();
		planList.add("SNAP");
		planList.add("QHP");
		planList.add("MEDAID");
		planList.add("CCAP");

		Mockito.when(dcMgmtService.showallPlanNames()).thenReturn(planList);

		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.get("/dc-api/planNames");

		MvcResult result = mockMvc.perform(builder).andReturn();
		int status = result.getResponse().getStatus();
		assertEquals(200, status);
	}

	@Test
	void generateCaseNoTest1() throws Exception {
		Mockito.when(dcMgmtService.generateCaseNumber(1)).thenReturn(1001);

		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post("/dc-api/generateCaseNo/1");
		MvcResult result = mockMvc.perform(builder).andReturn();
		int status = result.getResponse().getStatus();
		assertEquals(201, status);
	}

	@Test
	void generateCaseNoTest2() throws Exception {
		Mockito.when(dcMgmtService.generateCaseNumber(121)).thenReturn(0);

		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post("/dc-api/generateCaseNo/121");
		MvcResult result = mockMvc.perform(builder).andReturn();
		int status = result.getResponse().getStatus();
		assertEquals(201, status);
	}

	@Test
	void updatePlansectionTest1() throws Exception {

		PlanSelectionInputs input = new PlanSelectionInputs();
		input.setAppId(2001);
		input.setCaseNo(123);
		input.setPlanId(1);

		Mockito.when(dcMgmtService.savePlanSelection(input)).thenReturn(0);

		ObjectMapper mapper = new ObjectMapper();
		String valueAsString = mapper.writeValueAsString(input);

		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.put("/dc-api/updatePlanSelection")
				.contentType("application/json").content(valueAsString);
		MvcResult result = mockMvc.perform(builder).andReturn();
		String responseContent = result.getResponse().getContentAsString();
		assertEquals(0, Integer.parseInt(responseContent));
	}

	@Test
	void updatePlansectionTest2() throws Exception {

		PlanSelectionInputs input = new PlanSelectionInputs();
		input.setAppId(1);
		input.setCaseNo(1001);
		input.setPlanId(1);

		Mockito.when(dcMgmtService.savePlanSelection(input)).thenReturn(1001);

		ObjectMapper mapper = new ObjectMapper();
		String valueAsString = mapper.writeValueAsString(input);

		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.put("/dc-api/updatePlanSelection")
				.contentType("application/json").content(valueAsString);
		MvcResult result = mockMvc.perform(builder).andReturn();
		int status = result.getResponse().getStatus();
		assertEquals(200, status);
	}

	@Test
	void saveIncomeTest1() throws Exception {

		IncomeInputs input = new IncomeInputs();
		input.setCaseNo(1001);
		input.setEmployeeIncome(2000.0);
		input.setPropertyIncome(3000000.0);

		Mockito.when(dcMgmtService.saveIncomeDetails(input)).thenReturn(1001);

		ObjectMapper mapper = new ObjectMapper();
		String valueAsString = mapper.writeValueAsString(input);

		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post("/dc-api/saveIncome")
				.contentType("application/json").content(valueAsString);
		MvcResult result = mockMvc.perform(builder).andReturn();
		int status = result.getResponse().getStatus();
		assertEquals(201, status);
	}

	@Test
	void saveEductionTest1() throws Exception {

		EductionInputs input = new EductionInputs();
		input.setCaseNo(1001);
		input.setHighestQlfy("B.Tech");
		input.setPassOutYear(2020);

		Mockito.when(dcMgmtService.saveEductionDetails(input)).thenReturn(1001);

		ObjectMapper mapper = new ObjectMapper();
		String valueAsString = mapper.writeValueAsString(input);

		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post("/dc-api/saveEduction")
				.contentType("application/json").content(valueAsString);
		MvcResult result = mockMvc.perform(builder).andReturn();
		int status = result.getResponse().getStatus();
		//assertEquals(1001, Integer.parseInt(responseContent));
		assertEquals(201, status);
	}

	@Test
	void saveSaveChildernTest1() throws Exception {

	    ChildInputs input1 = new ChildInputs();
	    input1.setCaseNo(1001);
	    input1.setChildSSN(999945555L);
	    input1.setChildDOB(LocalDate.of(2000, 10, 21));

	    ChildInputs input2 = new ChildInputs();
	    input2.setCaseNo(1001);
	    input2.setChildSSN(999145555L);
	    input2.setChildDOB(LocalDate.of(2000, 11, 11));

	    List<ChildInputs> inputList = new ArrayList<>();
	    inputList.add(input2);
	    inputList.add(input1);

	    Mockito.when(dcMgmtService.saveChildernDetails(inputList)).thenReturn(1001);

	    ObjectMapper mapper = new ObjectMapper();
	    mapper.registerModule(new JavaTimeModule());
	    mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
	    String valueAsString = mapper.writeValueAsString(inputList);

	    MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post("/dc-api/saveChilds")
	            .contentType("application/json").content(valueAsString);
	    MvcResult result = mockMvc.perform(builder).andReturn();
	    int status = result.getResponse().getStatus();
	    assertEquals(201, status);
	}


	@Test
	void showSummaryReportTest() throws Exception {

		DcSummaryReport report = new DcSummaryReport();

		IncomeInputs input1 = new IncomeInputs();
		input1.setCaseNo(1001);
		input1.setEmployeeIncome(2000.0);
		input1.setPropertyIncome(3000000.0);

		EductionInputs input2 = new EductionInputs();
		input2.setCaseNo(1001);
		input2.setHighestQlfy("B.Tech");
		input2.setPassOutYear(2020);

		ChildInputs input3 = new ChildInputs();
		input3.setCaseNo(1001);
		input3.setChildSSN(999945555L);
		input3.setChildDOB(LocalDate.of(2000, 10, 21));

		ChildInputs input4 = new ChildInputs();
		input4.setCaseNo(1001);
		input4.setChildSSN(999145555L);
		input4.setChildDOB(LocalDate.of(2000, 11, 11));

		List<ChildInputs> inputList = List.of(input3, input4);

		report.setChildDetails(inputList);
		report.setEductionDetails(input2);
		report.setIncomeDetails(input1);
		report.setCitizenDetails(null);
		report.setPlanName("SANP");

		Mockito.when(dcMgmtService.showDcSummary(10001)).thenReturn(report);

		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.get("/dc-api/summary/1001");
		MvcResult result = mockMvc.perform(builder).andReturn();
		int status = result.getResponse().getStatus();
		assertEquals(200, status);
	}

}
