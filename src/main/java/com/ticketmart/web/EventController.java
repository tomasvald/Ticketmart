package com.ticketmart.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.ticketmart.entities.Event;
import com.ticketmart.service.EventService;
import com.ticketmart.views.EventListSerializer;
import com.ticketmart.views.EventSerializer;

@RequestMapping("/events")
@Controller
public class EventController {
	
	@Autowired
	private EventService eventService;
	
	// objects for JSON serialization
	private ObjectMapper eventMapper;
	private SimpleModule eventModule;
	private EventSerializer eventSerializer;	

	private ObjectMapper eventListMapper;
	private SimpleModule eventListModule;
	private EventListSerializer eventListSerializer;
	
	public EventController() {		
		this.eventMapper = new ObjectMapper();
		this.eventModule = new SimpleModule();
		this.eventSerializer = new EventSerializer();
		
		this.eventModule.addSerializer(Event.class, eventSerializer);
		this.eventMapper.registerModule(eventModule);
		
		this.eventListMapper = new ObjectMapper();
		this.eventListModule = new SimpleModule();
		this.eventListSerializer = new EventListSerializer();
		
		this.eventListModule.addSerializer(Object.class, eventListSerializer);
		this.eventListMapper.registerModule(eventListModule);		
	}
	
	@RequestMapping(method=RequestMethod.GET, produces="application/json")
	public ResponseEntity<String> getEvents() throws JsonProcessingException {
		List<Event> events = eventService.findAll();		
		return ResponseEntity.status(HttpStatus.OK)
							 .body(eventListMapper.writeValueAsString(events));
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET, produces="application/json")
	public ResponseEntity<String> getEvent(@PathVariable Long id) throws JsonProcessingException {
		Event event = eventService.findById(id);
		
		if(event == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(eventMapper.writeValueAsString(null));
		}
		
		return ResponseEntity.status(HttpStatus.OK).body(eventMapper.writeValueAsString(event));
	}
}
