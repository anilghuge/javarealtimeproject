package com.example.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name="JR701_CO_TRIGGERS")
@Data
public class COTriggersEntity {

	@Id
	@SequenceGenerator(name="co_trigger_seq",sequenceName = "co_trigger_id_seq",initialValue = 1000,allocationSize = 1)
	@GeneratedValue(generator = "co_trigger_seq",strategy = GenerationType.SEQUENCE)
	private Integer triggerId;
	private Integer caseNo;
	@Lob
	private byte[] coNoticePdf;
	@Column(length = 30)
	private String triggerStatus="Pending";
}
