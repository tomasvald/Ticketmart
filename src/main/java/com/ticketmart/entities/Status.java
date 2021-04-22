package com.ticketmart.entities;

import javax.persistence.*;

@Entity
@Table(name = "status")
public class Status {
	
	public static final int AVAILABLE = 1;
	public static final int RESERVED  = 2;
	public static final int SOLD      = 3;
	
	private int idStatus;
	private String status;
	private String description;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idStatus")
	public int getIdStatus() {
		return idStatus;
	}
	
	public void setIdStatus(int idStatus) {
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
