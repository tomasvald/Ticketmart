package com.ticketmart.entities;

import javax.persistence.*;

@Entity
@Table(name = "status")
public class Status {
	
	public static final Long AVAILABLE = 1l;
	public static final Long RESERVED  = 2l;
	public static final Long SOLD      = 3l;
	
	private Long idStatus;
	private String status;
	private String description;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idStatus")
	public Long getIdStatus() {
		return idStatus;
	}
	
	public void setIdStatus(Long idStatus) {
		this.idStatus = idStatus;
	}
	
	@Column(name = "status")
	public String getStatus() {
		return status;
	}
	
	public void setStatus(String status) {
		this.status = status;
	}
	
	@Column(name = "description")
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}

}
