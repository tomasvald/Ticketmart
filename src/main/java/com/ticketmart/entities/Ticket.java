package com.ticketmart.entities;

import javax.persistence.*;

@Entity
@Table(name="ticket")
public class Ticket {
	
	private Long idTicket;
	private String seatNumber;
	
	private Status status;
	private Section section;
	
	public Ticket() {
		this.status = new Status();
		this.section = new Section();
	}
	
	// getter methods

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="idTicket")
	public Long getIdTicket() {
		return idTicket;
	}
	
	@Column(name="seatNumber")
	public String getSeatNumber() {
		return seatNumber;
	}

	@ManyToOne
	@JoinColumn(name="idStatus")
	public Status getStatus() {
		return status;
	}

	@ManyToOne
	@JoinColumn(name="idSection")
	public Section getSection() {
		return section;
	}
	
	// setter methods
	
	public void setIdTicket(Long idTicket) {
		this.idTicket = idTicket;
	}
	
	public void setSeatNumber(String seatNumber) {
		this.seatNumber = seatNumber;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public void setSection(Section section) {
		this.section = section;
	}
	
	//
	
	@Override
	public String toString(){
		return "Ticket - idTicket: " + idTicket	+ " seatNumber: " + seatNumber
				+ " Status: " + getStatus().getStatus();
	}
}
