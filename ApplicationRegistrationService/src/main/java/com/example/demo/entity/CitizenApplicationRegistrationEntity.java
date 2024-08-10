package com.example.demo.entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "CITIZEN_APPLICATION")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class CitizenApplicationRegistrationEntity extends AuditBase {

	@Id
	@SequenceGenerator(name="gen1_seq",sequenceName = "app_id_seq",initialValue = 1000,allocationSize = 1)
	@GeneratedValue(generator = "gen1_seq",strategy = GenerationType.SEQUENCE)
	private Integer appId;
	@Column(length = 30)
	private String fullName;
	@Column(length = 30)
	private String email;
	@Column(length = 1)
	private String gender;
	private Long phoneNo;
	private Long ssn;
	@Column(length = 30)
	private String stateName;
	private LocalDate dob;
	
}
