package com.ticketmart.test;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;

import com.ticketmart.config.DbConfig;
import com.ticketmart.dao.DbOperationsDao;
import com.ticketmart.entities.Event;
import com.ticketmart.entities.Participant;
import com.ticketmart.entities.Section;
import com.ticketmart.entities.Ticket;

public class DbConfigTest {
	
	private static Logger logger = Logger.getLogger(DbConfigTest.class);
	
	GenericApplicationContext ctx;
	DbOperationsDao dbOperations;
	
	@Before
	public void setUp() {
		ctx = new AnnotationConfigApplicationContext(DbConfig.class);
		dbOperations = ctx.getBean("dbOperations", DbOperationsDao.class);
		assertNotNull(dbOperations);
	}
	
	@Test
	public void test1() {
		List<Event> events = dbOperations.getEvents();
		for(Event event : events) {
			logger.info(event.toString());
		}
	}
	
	@Test
	public void test2() {
		Event event = dbOperations.getEventWithSections(2);
		logger.info(event.toString());
		if(event.getVenue() != null) {
			logger.info(event.getVenue().toString());
		}
		if(event.getSections() != null) {
			for (Section section : event.getSections()) {
				logger.info(section.toString());
			}
		}
	}
	
	// List available tickets for a section
	@Test
	public void test3() {
		Section section = dbOperations.getSectionWithTickets(3);
		logger.info(section.toString());
		if(section.getTickets() != null) {
			for(Ticket ticket : section.getTickets()) {
				logger.info(ticket.toString());
			}
		}
	}
	
	// Reserve some tickets for purchase, and then list all section tickets
	// to verify the tickets were reserved
	@Test
	public void test4() {
		List<Ticket> reserved = dbOperations.getAvailableTickets(3, 2);
		logger.info("Reserved tickets");
		if(reserved.size() > 0) {
			for(Ticket ticket : reserved) {
				logger.info(ticket.toString());
			}
		}
		
		Section section = dbOperations.getSectionWithTickets(3);
		logger.info(section.toString());
		if(section.getTickets() != null) {
			for(Ticket ticket : section.getTickets()) {
				logger.info(ticket.toString());
			}
		}
	}
	
	@After
	public void tearDown() {
		ctx.close();
	}

}
