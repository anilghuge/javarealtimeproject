package com.example.demo.entity;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name="JR701_DC_CHILDERN")
@Data
public class DCChildernEntity {

	@Id
	@SequenceGenerator(name="dc_childern_seq",sequenceName = "dc_childern_id_seq",initialValue = 1000,allocationSize = 1)
	@GeneratedValue(generator = "dc_childern_seq",strategy = GenerationType.SEQUENCE)
	private Integer childId;
	private LocalDate childDOB;
	private Long childSSN;
	private Integer caseNo;
}
