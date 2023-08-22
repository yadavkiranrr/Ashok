package com.kiran.it.entity;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;


@Data
@Entity
@Table(name = "ELIGIBILITY_DETAILS")
public class EligibilityDetails {
	
	
	@Id
	@GeneratedValue
	private Integer eligId;
	
	private String name;
	
	private Long mobile;
	
	private String email;
	
	private Long ssn;
	
	private String planName;
	
	private String planStatus;
	
	private LocalDate planStartDate;
	
	private LocalDate planEndDate;
	
	private LocalDate createDate;
	
	private LocalDate updateDate;
	
	private String createdBy;
	
	private String updatedBy;
	
	

}
