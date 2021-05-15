package com.ticketmart.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ticketmart.entities.Event;
import com.ticketmart.entities.Participant;
import com.ticketmart.entities.Section;
import com.ticketmart.entities.Status;
import com.ticketmart.entities.Ticket;
import com.ticketmart.entities.Venue;

@Transactional
@Repository("dataService")
public class DataServiceImpl implements DataService {
	
	private static Logger logger = Logger.getLogger(DataServiceImpl.class);
	
	@PersistenceContext
	private EntityManager em;
	
	// ------------- STATUS METHODS
	
	@Override
	@Transactional(readOnly=true)
	public Status getStatus(int idStatus) {
		String query = "select distinct s from Status s where s.idStatus = :id";
		return em.createQuery(query, Status.class)
				 .setParameter("id", idStatus)
				 .getSingleResult();
	}
	
	// ------------- EVENT METHODS
	
	/*
	 * Returns event with information on venue, participants and section
	 */
	@Override
	@Transactional(readOnly=true)
	public Event getEvent(int idEvent) {
		String query = "select distinct e from Event e " +
						"left join fetch e.participants p " +
						"left join fetch e.sections s " +
						"where e.id = :id";
		return em.createQuery(query, Event.class)
				 .setParameter("id", idEvent)
				 .getSingleResult();
	}
	
	/*
	 * Returns list of events with information about the venue
	 */
	@Override
	@Transactional(readOnly=true)
	public List<Event> getEvents() {
		String query = "select e from Event e";
		return em.createQuery(query, Event.class)
				 .getResultList();
	}
	
	// ------------- VENUE METHDOS
	
	@Override
	@Transactional(readOnly=true)
	public Venue getVenue(int idVenue) {
		String query = "select distinct v from Venue v where v.idVenue = :id";
		return em.createNamedQuery(query, Venue.class)
				 .setParameter("id", idVenue)
				 .getSingleResult();
	}
	
	@Override
	@Transactional(readOnly=true)
	public List<Venue> getVenues() {
		String query = "select v from Venue v";
		return em.createQuery(query, Venue.class)
				 .getResultList();
	}
	
	// ------------- PARTICIPANTS METHODS
	
	@Override
	@Transactional(readOnly=true)
	public Participant getParticipant(int idParticipant) {
		String query = "select distinct p from Participant p " +
						"left join fetch p.events e " +
						"where p.idParticipant = :id ";
		return em.createNamedQuery(query, Participant.class)
				 .setParameter("id", idParticipant)
				 .getSingleResult();
	}
	
	@Override
	@Transactional(readOnly=true)
	public List<Participant> getParticipants(){
		String query = "select p from Participant p " +
						"order by p.name";
		return em.createNamedQuery(query, Participant.class)
				 .getResultList();
	}
	
	// ------------- SECTION METHODS
	
	/*
	 * Return section information, without data about its event or tickets
	 */
	@Override
	@Transactional(readOnly=true)
	public Section getSection(int idSection) {
		String query = "from Section s where s.idSection = :id";
		return em.createQuery(query, Section.class)
				 .setParameter("id", idSection)
				 .getSingleResult();
	}
	
	// ------------- TICKET METHODS

	/*
	 * Return list of all tickets assigned to an event section
	 */
	@Override
	@Transactional(readOnly=true)
	public List<Ticket> getTickets(int idSection){
		String query = "from Ticket where section.idSection = :id";
		return em.createQuery(query, Ticket.class)
				 .setParameter("id", idSection)
				 .getResultList();
	}
	
	/*
	 * Return list of all tickets available in an event section
	 */
	private List<Ticket> getTicketsAvailable(int idSection) {
		logger.info("Querying all available tickets for a section");
		String query = "from Ticket t " +
						"where t.section.idSection = :id " +
						"and t.status.idStatus = 1";
		List<Ticket> ticketsAvailable = 
				em.createQuery(query, Ticket.class)
				.setParameter("id", idSection)
				.getResultList();
		return ticketsAvailable;
	}
	
	/*
	 * Return list of a specific amount of tickets available for a section
	 */
	@Override
	public synchronized List<Ticket> getTicketsAvailable(int idSection, int amountOfTickets) {
		List<Ticket> ticketsAvailable = getTicketsAvailable(idSection);
		
		if(ticketsAvailable.size() < amountOfTickets) {
			return new ArrayList<Ticket>();
		}		

		logger.info("Reserving some tickets");
		List<Ticket> requested = ticketsAvailable.subList(0, amountOfTickets);		
		return reserveTickets(requested);
	}
	
	@Override
	public Ticket save(Ticket ticket) {
		em.merge(ticket);
		logger.info("Ticket saved - idTicket: " + ticket.getIdTicket());
		return ticket;
	}
	
	private List<Ticket> reserveTickets(List<Ticket> tickets){
		Status reserved = getStatus(Status.RESERVED);
		for(Ticket ticket : tickets) {
			ticket.setStatus(reserved);
			save(ticket);
		}
		logger.info(tickets.size() + " tickets reserved");
		return tickets;
	}

	@Override
	public List<Ticket> cancelPurchase(List<Ticket> tickets) {
		Status rollback = getStatus(Status.AVAILABLE);
		for(Ticket ticket : tickets) {
			ticket.setStatus(rollback);
			save(ticket);
		}
		logger.info(tickets.size() + " tickets cancelled");
		return tickets;
	}

	@Override
	public List<Ticket> confirmPurchase(List<Ticket> tickets) {
		Status confirmTickets = getStatus(Status.SOLD);
		for(Ticket ticket : tickets) {
			ticket.setStatus(confirmTickets);
			save(ticket);
		}
		return tickets;
	}

}
