package com.example.crud.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name="tbl_mst_travel_plan")
public class TravelPlan extends BaseParams{

	@Id
	@Column(name="plan_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer planId;
	@Column(name="plan_name",length = 30)
	private String planName;
	
	@Column(name="plan_min_budget")
	private Double planMinBudget;
	
	@Column(name="plan_description")
	private String planDescription;
	
	@Column(name="plan_category_id")
	private Integer planCategoryId;
}
