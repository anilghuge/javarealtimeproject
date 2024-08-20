package com.example.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name="JR701_DC_INCOME")
@Data
public class DCIncomeEntity {

	@Id
	@SequenceGenerator(name="dc_income_seq",sequenceName = "dc_income_seq",initialValue = 1000,allocationSize = 1)
	@GeneratedValue(generator = "dc_income_seq",strategy = GenerationType.SEQUENCE)
	private Integer incomeId;
	private Integer caseNo;
	private Double employeeIncome;
	private Double propertyIncome;
	
}
