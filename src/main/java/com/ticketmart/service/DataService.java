package com.ticketmart.service;

import java.util.List;

import com.ticketmart.entities.Event;
import com.ticketmart.entities.Section;
import com.ticketmart.entities.Status;
import com.ticketmart.entities.Ticket;

public interface DataService {
	
	Status       getStatus(int idStatus);	
	Event        getEvent(int idEvent);
	List<Event>  getEvents();
	Section      getSection(int idSection);	
	Ticket       save(Ticket ticket);	
	List<Ticket> getTickets(int idSection);
	List<Ticket> getTicketsAvailable(int idSection, int amountOfTickets);
	List<Ticket> cancelPurchase(List<Ticket> tickets);
	List<Ticket> confirmPurchase(List<Ticket> tickets);
}
