package com.example.demo.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name="JR701_PLAN_MASTER")
@Data
public class PlanEntity {

	@Id
	@SequenceGenerator(name="plan_seq",sequenceName = "plan_id_seq",initialValue = 1000,allocationSize = 1)
	@GeneratedValue(generator = "plan_seq",strategy = GenerationType.SEQUENCE)
	private Integer planId;
	private String planName;
	private LocalDate startDate;
	private LocalDate endDate;
	private String description;
	private String activeSw;
	@CreationTimestamp
	@Column(updatable = false)
	private LocalDateTime creationDate;
	@UpdateTimestamp
	@Column(insertable = false)
	private LocalDateTime updatetionDate;
	@Column(length = 30)
	private String createdBy;
	@Column(length = 30)
	private String updatedBy;
}
