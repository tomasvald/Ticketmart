package com.ticketmart.client;

public class TicketRequest {
	
	private int idSection;
	private int amountOfTickets;
	
	public TicketRequest(int idSection, int amountOfTickets) {
		this.idSection = idSection;
		this.amountOfTickets = amountOfTickets;
	}
	
	public TicketRequest() {
		
	}

	public int getIdSection() {
		return idSection;
	}

	public void setIdSection(int idSection) {
		this.idSection = idSection;
	}

	public int getAmountOfTickets() {
		return amountOfTickets;
	}

	public void setAmountOfTickets(int amountOfTickets) {
		this.amountOfTickets = amountOfTickets;
	}	

}
