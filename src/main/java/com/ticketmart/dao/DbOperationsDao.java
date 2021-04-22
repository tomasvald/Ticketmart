package com.ticketmart.dao;

import java.util.List;

import com.ticketmart.entities.Event;
import com.ticketmart.entities.Section;
import com.ticketmart.entities.Status;
import com.ticketmart.entities.Ticket;

public interface DbOperationsDao {
	
	Status  getStatus(int idStatus);
	Event   getEvent(int idEvent);
	Event   getEventWithSections(int idEvent);
	Section getSection(int idSection);
	Section getSectionWithTickets(int idSection);
	Section getSectionWithAvailableTickets(int idSection);
	Ticket  save(Ticket ticket);
	
	List<Event> getEvents();
	List<Event> getEventsWithParticipant();
	
	List<Ticket> getTickets(int idSection);
	List<Ticket> getAvailableTickets(int idSection, int amountOfTickets);
	List<Ticket> cancelPurchase(List<Ticket> tickets);
	List<Ticket> confirmPurchase(List<Ticket> tickets);
}
