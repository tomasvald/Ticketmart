package com.ticketmart.test;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;

import com.ticketmart.config.DataServiceConfig;
import com.ticketmart.entities.Event;
import com.ticketmart.entities.Participant;
import com.ticketmart.entities.Ticket;
import com.ticketmart.entities.Venue;
import com.ticketmart.service.EventService;
import com.ticketmart.service.ParticipantService;
import com.ticketmart.service.StatusService;
import com.ticketmart.service.TicketService;
import com.ticketmart.service.VenueService;

public class DataServiceWithReposTest {
	
	GenericApplicationContext ctx;
	EventService eventService;
	VenueService venueService;
	ParticipantService participantService;
	StatusService statusService;
	TicketService ticketService;
	
	@Before
	public void setUp() {
		ctx = new AnnotationConfigApplicationContext(DataServiceConfig.class);
		eventService = ctx.getBean("eventService", EventService.class);
		venueService = ctx.getBean("venueService", VenueService.class);
		participantService = ctx.getBean("participantService", ParticipantService.class);
		statusService = ctx.getBean("statusService", StatusService.class);
		ticketService = ctx.getBean("ticketService", TicketService.class);
		
		assertNotNull(eventService);
		assertNotNull(venueService);
		assertNotNull(participantService);
		assertNotNull(statusService);
		assertNotNull(ticketService);
	}
	
	@Test
	public void testEvents() {
		System.out.println("Listing all events");
		for(Event event : eventService.findAll()) {
			System.out.println(event);
		}
		System.out.println("Querying an event");
		System.out.println(eventService.findById(2l));
		System.out.println("Querying an event by name");
		for(Event event : eventService.findByName("metallica")) {
			System.out.println(event);
		}		
	}
	
	@Test
	public void testVenues() {
		System.out.println("Listing all venues");
		for(Venue venue : venueService.findAll()) {
			System.out.println(venue);
		}
		System.out.println("Quering a venue");
		System.out.println(venueService.findById(4l));
	}
	
	@Test
	public void testParticipants() {
		System.out.println("Listing all participants");
		for(Participant participant : participantService.findAll()) {
			System.out.println(participant);
		}
		System.out.println("Quering a participant");
		System.out.println(participantService.findById(6l));
	}
	
	@Test
	public void testTickets() {
		System.out.println("Listing all tickets from a section");
		for(Ticket ticket : ticketService.findBySection(3l)) {
			System.out.println(ticket);
		}
		
		System.out.println("Purchasing some tickets");
		List<Ticket> purchased = ticketService.findAvailableAndPurchase(3l, 2);
		for(Ticket ticket : purchased) {
			System.out.println(ticket);
		}
		
		System.out.println("Listing all tickets from a section");
		for(Ticket ticket : ticketService.findBySection(3l)) {
			System.out.println(ticket);
		}
	}
	
	@After
	public void tearDown() {
		ctx.close();
	}
	
}
