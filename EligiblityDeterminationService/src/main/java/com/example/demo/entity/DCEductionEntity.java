package com.example.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name="JR701_DC_EDUCTION")
@Data
public class DCEductionEntity {

	@Id
	@SequenceGenerator(name="dc_eduction_seq",sequenceName = "dc_eduction_id_seq",initialValue = 1000,allocationSize = 1)
	@GeneratedValue(generator = "dc_eduction_seq",strategy = GenerationType.SEQUENCE)
	private Integer eductionId;
	private Integer caseNo;
	@Column(length = 40)
	private String highestQlfy;
	private Integer passOutYear;	
}
