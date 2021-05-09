package com.ticketmart.web;

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
import com.ticketmart.service.DataService;
import com.ticketmart.views.EventListSerializer;
import com.ticketmart.views.EventSerializer;

@RequestMapping("/events")
@Controller
public class EventController {
	
	private DataService dataService;
	
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
		return ResponseEntity.status(HttpStatus.OK)
							 .body(eventListMapper.writeValueAsString(dataService.getEvents()));
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET, produces="application/json")
	public ResponseEntity<String> getEvent(@PathVariable int id) throws JsonProcessingException {
		Event event = dataService.getEvent(id);
		
		if(event == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(eventMapper.writeValueAsString(null));
		}
		
		return ResponseEntity.status(HttpStatus.OK).body(eventMapper.writeValueAsString(event));
	}
	
	// ------------------------------------------------
	
	@Autowired
	public void setDataService(DataService dataService) {
		this.dataService = dataService;
	}

}
