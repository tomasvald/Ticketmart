package com.ticketmart.service;

import java.util.List;

import com.ticketmart.entities.Ticket;

public interface TicketService {
	Ticket        save(Ticket ticket);	
	List<Ticket>  findBySection(Long idSection);
	List<Ticket>  findAvailable(Long idSection, int amountOfTickets);
	List<Ticket>  findAvailableAndPurchase(Long idSection, int amountOfTickets);
	List<Ticket>  purchaseTickets(List<Ticket> tickets);
	List<Ticket>  reserveTickets(List<Ticket> tickets);
	List<Ticket>  cancelPurchase(List<Ticket> tickets);

}
