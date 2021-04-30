package com.ticketmart.client;

public class TicketRequest {
	
	private int idSection;
	private int amountOfTickets;
	
	private Client client;
	
	public TicketRequest() {}

	public TicketRequest(int idSection, int amountOfTickets, Client client) {
		this.idSection = idSection;
		this.amountOfTickets = amountOfTickets;
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
	
	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

}
