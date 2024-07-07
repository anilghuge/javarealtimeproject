package com.example.crud.entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@MappedSuperclass
public class BaseParams {

	@Column(name = "created_by",length =20)
	private String createdBy;

	@Column(name = "updated_by",length =20)
	private String updatedBy;

	@Column(name = "comments")
	private String comments;

	@Column(name = "is_active")
	private String active;

	@Column(name = "created_date",updatable = false)
	@CreationTimestamp
	private LocalDateTime createdDate;

	@Column(name = "updated_date",updatable = true,insertable = false)
	@UpdateTimestamp
	private LocalDateTime updatedDate;

}
