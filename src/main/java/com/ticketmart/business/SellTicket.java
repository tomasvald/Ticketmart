package com.ticketmart.business;

import java.util.List;

import org.apache.log4j.Logger;

import com.ticketmart.client.Client;
import com.ticketmart.client.TicketRequest;
import com.ticketmart.dao.DbOperationsDao;
import com.ticketmart.entities.Ticket;

public class SellTicket {
	
	private Logger logger = Logger.getLogger(SellTicket.class);
	
	private DbOperationsDao database;
	
	private Client client;
	private TicketRequest ticketRequest;
	
	public SellTicket(Client client, TicketRequest ticketRequest) {
		this.client = client;
		this.ticketRequest = ticketRequest;
	}
	
	public SellTicket() {
		List<Ticket> tickets = 
				database.getAvailableTickets(
						ticketRequest.getIdSection(), 
						ticketRequest.getAmountOfTickets());
		logger.info("Tickets available - " + tickets.toString());
		
		boolean acceptTickets = true;
		
		if(acceptTickets) {
			database.confirmPurchase(tickets);
			logger.info("Tickets purchase completed");
		}
		else {
			database.cancelPurchase(tickets);
			logger.info("Ticket purchase cancelled");
		}
	}
	
	public void sendRequest() {
		
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public TicketRequest getTicketRequest() {
		return ticketRequest;
	}

	public void setTicketRequest(TicketRequest ticketRequest) {
		this.ticketRequest = ticketRequest;
	}
	
	
	
	

}
