package com.example.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name="JR701_DC_CASES")
@Data
public class DCCaseEntity {

	@Id
	@SequenceGenerator(name="dc_case_seq",sequenceName = "dc_case_id_seq",initialValue = 1000,allocationSize = 1)
	@GeneratedValue(generator = "dc_case_seq",strategy = GenerationType.SEQUENCE)
	private Integer caseNo;
	private Integer appId;
	private Integer planId;
}
