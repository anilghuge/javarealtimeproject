package com.example.user.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="tbl_mst_user")
public class UserMaster {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer userId;
	private String name;
	private String password;
	@Column(unique = true,nullable = false)
	private String email;
	private Long mobileNumber;
	private Long aadharNumber;
	private String gender;
	private LocalDate dob;
	private String active;
	
	@CreationTimestamp
	@Column(updatable = false,insertable = true)
	private LocalDateTime createdOn;
	@UpdateTimestamp
	@Column(insertable = false,updatable = true)
	private LocalDateTime updatedOn;
	private String createdBy;
	private String updatedBy;
	
}
