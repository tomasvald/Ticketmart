package com.ticketmart.test;

import static org.junit.Assert.*;

import java.util.List;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;

import com.ticketmart.config.DataServiceConfig;
import com.ticketmart.entities.Event;
import com.ticketmart.entities.Section;
import com.ticketmart.entities.Ticket;
import com.ticketmart.service.DataService;

public class DbConfigTest {
	
	private static Logger logger = Logger.getLogger(DbConfigTest.class);
	
	GenericApplicationContext ctx;
	DataService dataService;
	
	@Before
	public void setUp() {
		ctx = new AnnotationConfigApplicationContext(DataServiceConfig.class);
		dataService = ctx.getBean("dataService", DataService.class);
		assertNotNull(dataService);
	}
	
	@Test
	// List all events
	public void test1() {
		List<Event> events = dataService.getEvents();
		for(Event event : events) {
			logger.info(event.toString());
		}
	}
	
	@Test
	// Show information of a specific event
	public void test2() {
		Event event = dataService.getEvent(2);
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
	
	// List all tickets for a section
	@Test
	public void test3() {
		List<Ticket> tickets = dataService.getTickets(3);
		if(tickets != null) {
			for(Ticket ticket : tickets) {
				logger.info(ticket.toString());
			}
		}
	}
	
	// Reserve some tickets for purchase, and then list all section tickets
	// to verify the tickets were reserved
	@Test
	public void test4() {
		List<Ticket> reserved = dataService.getTicketsAvailable(3, 2);
		logger.info("Reserved tickets");
		if(reserved.size() > 0) {
			for(Ticket ticket : reserved) {
				logger.info(ticket.toString());
			}
		}
		
		List<Ticket> tickets = dataService.getTickets(3);
		logger.info(dataService.getSection(3));
		if(tickets != null) {
			for(Ticket ticket : tickets) {
				logger.info(ticket.toString());
			}
		}
	}
	
	@After
	public void tearDown() {
		ctx.close();
	}

}
