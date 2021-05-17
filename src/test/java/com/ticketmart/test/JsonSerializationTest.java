package com.ticketmart.test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.ticketmart.config.DataServiceConfig;
import com.ticketmart.entities.Event;
import com.ticketmart.entities.Participant;
import com.ticketmart.entities.Venue;
import com.ticketmart.service.EventService;
import com.ticketmart.service.ParticipantService;
import com.ticketmart.service.StatusService;
import com.ticketmart.service.TicketService;
import com.ticketmart.service.VenueService;
import com.ticketmart.views.EventListSerializer;
import com.ticketmart.views.EventSerializer;
import com.ticketmart.views.ParticipantListSerializer;
import com.ticketmart.views.ParticipantSerializer;
import com.ticketmart.views.VenueListSerializer;
import com.ticketmart.views.VenueSerializer;

public class JsonSerializationTest {
	
	GenericApplicationContext ctx;
	EventService eventService;
	VenueService venueService;
	ParticipantService participantService;
	StatusService statusService;
	TicketService ticketService;
	
	ObjectMapper mapper;
	SimpleModule module;
	
	@Before
	public void setUp() {
		
		ctx = new AnnotationConfigApplicationContext(DataServiceConfig.class);
		eventService = ctx.getBean("eventService", EventService.class);
		venueService = ctx.getBean("venueService", VenueService.class);
		participantService = ctx.getBean("participantService", ParticipantService.class);
		statusService = ctx.getBean("statusService", StatusService.class);
		ticketService = ctx.getBean("ticketService", TicketService.class);
		
		mapper = new ObjectMapper();
		module = new SimpleModule();
		
		assertNotNull(eventService);
		assertNotNull(venueService);
		assertNotNull(participantService);
		assertNotNull(statusService);
		assertNotNull(ticketService);
	}
	
	@After
	public void tearDown() {
		ctx.close();
	}
	
	@Test
	public void testEventSerializer() throws JsonProcessingException {
		
		EventSerializer serializer = new EventSerializer();
		
		module.addSerializer(Event.class, serializer);
		mapper.registerModule(module);
		
		System.out.println("Requesting an event");
		System.out.println(mapper
				.writerWithDefaultPrettyPrinter()
				.writeValueAsString(eventService.findById(2l)));	
	}
	
	@Test
	public void testEventListSerializer() throws JsonProcessingException {
		
		EventListSerializer serializer = new EventListSerializer();
		
		module.addSerializer(Object.class, serializer);
		mapper.registerModule(module);
		
		System.out.println("Requesting all events");
		System.out.println(mapper
				.writerWithDefaultPrettyPrinter()
				.writeValueAsString(eventService.findAll()));	
	}
	
	@Test
	public void testParticipantSerializer() throws JsonProcessingException {
		
		ParticipantSerializer serializer = new ParticipantSerializer();
		
		module.addSerializer(Participant.class, serializer);
		mapper.registerModule(module);
		
		System.out.println("Requesting a participant");
		System.out.println(mapper
				.writerWithDefaultPrettyPrinter()
				.writeValueAsString(participantService.findById(3l)));		
		
	}

	@Test
	public void testParticipantListSerializer() throws JsonProcessingException {
		
		ParticipantListSerializer serializer = new ParticipantListSerializer();
		
		module.addSerializer(Object.class, serializer);
		mapper.registerModule(module);
		
		System.out.println("Requesting all participants");
		System.out.println(mapper
				.writerWithDefaultPrettyPrinter()
				.writeValueAsString(participantService.findAll()));		
		
	}
	
	@Test
	public void testVenueSerializer() throws JsonProcessingException {
		
		VenueSerializer serializer = new VenueSerializer();
		
		module.addSerializer(Venue.class, serializer);
		mapper.registerModule(module);
		
		System.out.println("Requesting a venue");
		System.out.println(mapper
				.writerWithDefaultPrettyPrinter()
				.writeValueAsString(venueService.findById(5l)));		
		
	}

	@Test
	public void testVenueListSerializer() throws JsonProcessingException {
		
		VenueListSerializer serializer = new VenueListSerializer();
		
		module.addSerializer(Object.class, serializer);
		mapper.registerModule(module);
		
		System.out.println("Requesting all venues");
		System.out.println(mapper
				.writerWithDefaultPrettyPrinter()
				.writeValueAsString(venueService.findAll()));		
		
	}

}
