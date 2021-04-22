package com.ticketmart.entities;

import javax.persistence.*;

@Entity
@Table(name="ticket")
public class Ticket {
	
	private int idTicket;
	private String seatNumber;
	
	private Status status;
	private Section section;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="idTicket")
	public int getIdTicket() {
		return idTicket;
	}
	
	public void setIdTicket(int idTicket) {
		this.idTicket = idTicket;
	}
	
	@Column(name="seatNumber")
	public String getSeatNumber() {
		return seatNumber;
	}
	
	public void setSeatNumber(String seatNumber) {
		this.seatNumber = seatNumber;
	}

	@ManyToOne
	@JoinColumn(name="idStatus")
	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	@ManyToOne
	@JoinColumn(name="idSection")
	public Section getSection() {
		return section;
	}

	public void setSection(Section section) {
		this.section = section;
	}
	
	@Override
	public String toString(){
		return "Ticket - idTicket: " + idTicket	+ " seatNumber: " + seatNumber
				+ " Status: " + getStatus().getStatus();
	}
}
