package com.ticketmart.db;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ticketmart.dao.DbOperationsDao;
import com.ticketmart.entities.Event;
import com.ticketmart.entities.Section;
import com.ticketmart.entities.Status;
import com.ticketmart.entities.Ticket;

@Transactional
@Repository("dbOperations")
public class DbOperations implements DbOperationsDao {
	
	private static Logger logger = Logger.getLogger(DbOperations.class);
	
	private SessionFactory sessionFactory;
	
	@Override
	public Status  getStatus(int idStatus) {
		return (Status) sessionFactory.getCurrentSession()
				.createQuery("select s from Status s where s.idStatus = :id")
				.setParameter("id", idStatus)
				.uniqueResult();
	}

	@Override
	public Event getEvent(int idEvent) {
		return (Event) sessionFactory.getCurrentSession()
				.createQuery("SELECT e FROM Event e WHERE e.idEvent = :id")
				.setParameter("id", idEvent)
				.uniqueResult();
	}
	
	@Override
	public Event getEventWithSections(int idEvent) {
		return (Event) sessionFactory.getCurrentSession()
				.getNamedQuery("Event.getEventWithSections")
				.setParameter("id", idEvent)
				.uniqueResult();
	}
	
	@Override
	public Section getSection(int idSection) {
		return (Section) sessionFactory.getCurrentSession()
				.createQuery("select s from Section where s.idSection = :id")
				.setParameter("id", idSection)
				.uniqueResult();
	}
	
	@Override
	public Section getSectionWithTickets(int idSection) {
		return (Section) sessionFactory.getCurrentSession()
				.getNamedQuery("Section.getSectionWithTickets")
				.setParameter("idSection", idSection)
				.uniqueResult();
	}
	
	@Override
	public Section getSectionWithAvailableTickets(int idSection) {
		return (Section) sessionFactory.getCurrentSession()
				.getNamedQuery("Section.getSectionWithAvailableTickets")
				.setParameter("idSection", idSection)
				.uniqueResult();
	}
	
	// Complex queries
	
	@Override
	@Transactional(readOnly=true)
	@SuppressWarnings("unchecked")
	public List<Event> getEvents() {
		return sessionFactory.getCurrentSession()
				.createQuery("SELECT e FROM Event e")
				.list();
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public List<Event> getEventsWithParticipant() {
		return sessionFactory.getCurrentSession()
				.getNamedQuery("Event.getEventsWithParticipant")
				.list();
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public List<Ticket> getTickets(int idSection){
		return sessionFactory.getCurrentSession()
				.createQuery("from Ticket where section.idSection = :id")
				.setParameter("id", idSection)
				.list();
	}
	
	@Override
	public List<Ticket> getAvailableTickets(int idSection, int amountOfTickets) {
		@SuppressWarnings("unchecked")
		List<Ticket> allTickets = sessionFactory.getCurrentSession()
				.createQuery("from Ticket where section.idSection = :id and status.idStatus = 1")
				.setParameter("id", idSection)
				.list();
		
		if(allTickets.size() < amountOfTickets) {
			return new ArrayList<Ticket>();
		}
		
		List<Ticket> available = allTickets.subList(0, amountOfTickets);		
		return reserveTickets(available);
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
		return tickets;
	}

	@Override
	public List<Ticket> cancelPurchase(List<Ticket> tickets) {
		Status rollback = getStatus(Status.AVAILABLE);
		for(Ticket ticket : tickets) {
			ticket.setStatus(rollback);
			save(ticket);
		}
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
