package com.example.crud.entity;

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
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "JR701_PLAN_CATEGORY")
public class PlanCategory {

	@Id
	@SequenceGenerator(name = "category_seq", sequenceName = "category_id_seq", initialValue = 1, allocationSize = 1)
	@GeneratedValue(generator = "category_seq", strategy = GenerationType.SEQUENCE)
	@Column(name = "category_id")
	private Integer categoryId;

	@Column(name = "category_name")
	private String categoryName;

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
