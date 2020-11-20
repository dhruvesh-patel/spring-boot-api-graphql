package com.dpinc.beneficiarymicroservice.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "BENEFICIARY")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Beneficiary {

	@Id
	@Column(name = "BENEFICIARY_ID")
	private Integer beneficiaryId;
	
	@Column(name = "BENEFICIARY_NAME")
	private String beneficiaryName;
	
	@Column(name = "BENEFICIARY_TYPE")
	private String beneficiaryType;
	
	@Column(name = "NATIONAL_ID")
	private String nationalId;
	
	@Column(name = "NATIONAL_ID_TYPE")
	private String nationalIdType;
	
	@Column(name = "SIC_CODE")
	private String sicCode;
	
	@Column(name = "ORG_SIZE")
	private String orgSize;
	
	@Column(name = "POST_CODE")
	private String postCode;
	
	@Column(name = "REGION")
	private String region;
	
	@Column(name = "COUNTRY")
	private String country;
	
	@Column(name = "CREATED_BY")
	private String createdBy;
	
	@Column(name = "APPROVED_BY")
	private String approvedBy;
	
	@Column(name = "STATUS")
	private String status;
	
	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CREATED_TIMESTAMP")
	private Date createdTimestamp;
	
	@UpdateTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "LAST_MODIFIED_TIMESTAMP")
	private Date lastModifiedTimestamp;
	
}
