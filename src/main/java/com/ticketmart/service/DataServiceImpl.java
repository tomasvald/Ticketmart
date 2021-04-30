package com.ticketmart.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ticketmart.entities.Event;
import com.ticketmart.entities.Section;
import com.ticketmart.entities.Status;
import com.ticketmart.entities.Ticket;

@Transactional
@Repository("dataService")
public class DataServiceImpl implements DataService {
	
	private static Logger logger = Logger.getLogger(DataServiceImpl.class);
	
	private SessionFactory sessionFactory;
	
	// Status methods
	
	@Override
	public Status  getStatus(int idStatus) {
		return (Status) sessionFactory.getCurrentSession()
				.createQuery("select s from Status s where s.idStatus = :id")
				.setParameter("id", idStatus)
				.uniqueResult();
	}
	
	// Event methods
	
	/*
	 * Returns event with information on venue, participants and section
	 */
	@Override
	public Event getEvent(int idEvent) {
		logger.info("Querying an event");
		return (Event) sessionFactory.getCurrentSession()
				.getNamedQuery("Event.getEventDetailed")
				.setParameter("id", idEvent)
				.uniqueResult();
	}
	
	/*
	 * Returns list of events with information about the venue
	 */
	@Override
	@Transactional(readOnly=true)
	@SuppressWarnings("unchecked")
	public List<Event> getEvents() {
		logger.info("Querying all events");
		return sessionFactory.getCurrentSession()
				.createQuery("SELECT e FROM Event e")
				.list();
	}
	
	// Section methods
	
	/*
	 * Return section information, without data about its event or tickets
	 */
	@Override
	public Section getSection(int idSection) {
		logger.info("Querying a section");
		return (Section) sessionFactory.getCurrentSession()
				.createQuery("select s from Section s where s.idSection = :id")
				.setParameter("id", idSection)
				.uniqueResult();
	}
	
	// Ticket methods

	/*
	 * Return list of all tickets assigned to an event section
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<Ticket> getTickets(int idSection){
		logger.info("Querying all tickets for a section");
		return sessionFactory.getCurrentSession()
				.createQuery("from Ticket where section.idSection = :id")
				.setParameter("id", idSection)
				.list();
	}
	
	/*
	 * Return list of all tickets available in an event section
	 */
	private List<Ticket> getTicketsAvailable(int idSection) {
		logger.info("Querying all available tickets for a section");
		@SuppressWarnings("unchecked")
		List<Ticket> ticketsAvailable = sessionFactory.getCurrentSession()
				.createQuery("from Ticket t where t.section.idSection = :id and t.status.idStatus = 1")
				.setParameter("id", idSection)
				.list();	
		return ticketsAvailable;
	}
	
	/*
	 * Return list of a specific amount of tickets available for a section
	 */
	@Override
	public List<Ticket> getTicketsAvailable(int idSection, int amountOfTickets) {
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
		sessionFactory.getCurrentSession().saveOrUpdate(ticket);
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
	
	// setter methods for DI injection with Spring

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	
	@Resource(name="sessionFactory")
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

}
