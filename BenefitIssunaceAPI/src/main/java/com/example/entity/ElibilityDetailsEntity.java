package com.example.entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Data;

@Table(name="JR701_ELIGIBLITY_DETERMINATION")
@Entity
@Data
public class ElibilityDetailsEntity {

	@Id
	@SequenceGenerator(name="ed_seq",sequenceName = "ed_id_seq",initialValue = 1000,allocationSize = 1)
	@GeneratedValue(generator = "ed_seq",strategy = GenerationType.SEQUENCE)
	private Integer edTracId;
	private Integer caseNo;
	@Column(length = 30)
	private String holderName;
	private Long holderSSN;
	@Column(length = 30)
	private String planName;
	@Column(length = 30)
	private String planStatus;
	private LocalDate planStartDate;
	private LocalDate planEndDate;
	private Double benifitAmt;
	@Column(length = 30)
	private String denialReason;
	private String bankName;
	private Long accountNumber;
	
}
