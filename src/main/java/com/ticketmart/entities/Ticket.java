package com.ticketmart.entities;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonView;
import com.ticketmart.views.View;

@Entity
@Table(name="ticket")
public class Ticket {
	
	private int idTicket;
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
	@JsonView(View.Summary.class)
	public int getIdTicket() {
		return idTicket;
	}
	
	@Column(name="seatNumber")
	@JsonView(View.Summary.class)
	public String getSeatNumber() {
		return seatNumber;
	}

	@ManyToOne
	@JoinColumn(name="idStatus")
	@JsonView(View.Detailed.class)
	public Status getStatus() {
		return status;
	}

	@ManyToOne
	@JoinColumn(name="idSection")
	@JsonView(View.Detailed.class)
	public Section getSection() {
		return section;
	}
	
	// setter methods
	
	public void setIdTicket(int idTicket) {
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
