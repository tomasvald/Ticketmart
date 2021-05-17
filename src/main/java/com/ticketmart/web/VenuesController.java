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
import com.ticketmart.entities.Venue;
import com.ticketmart.service.VenueService;
import com.ticketmart.views.VenueListSerializer;
import com.ticketmart.views.VenueSerializer;

@RequestMapping("/venues")
@Controller
public class VenuesController {
	
	@Autowired
	private VenueService venueService;
	
	// objects for JSON serialization
	private ObjectMapper venueMapper;
	private SimpleModule venueModule;
	private VenueSerializer venueSerializer;	
	
	private ObjectMapper venueListMapper;
	private SimpleModule venueListModule;
	private VenueListSerializer venueListSerializer;
	
	public VenuesController() {
		this.venueMapper = new ObjectMapper();
		this.venueModule = new SimpleModule();
		this.venueSerializer = new VenueSerializer();
		
		this.venueModule.addSerializer(Venue.class, venueSerializer);
		this.venueMapper.registerModule(venueModule);
		
		this.venueListMapper = new ObjectMapper();
		this.venueListModule = new SimpleModule();
		this.venueListSerializer = new VenueListSerializer();
		
		this.venueListModule.addSerializer(Object.class, venueListSerializer);
		this.venueListMapper.registerModule(venueListModule);
	}
	
	@RequestMapping(method = RequestMethod.GET, produces="application/json")
	public ResponseEntity<String> getVenues() throws JsonProcessingException{
		List<Venue> venues = venueService.findAll();
		return ResponseEntity.status(HttpStatus.OK)
							 .body(venueListMapper.writeValueAsString(venues));
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces="application/json")
	public ResponseEntity<String> getVenue(@PathVariable Long id) throws JsonProcessingException {
		Venue venue = venueService.findById(id);
		
		if(venue == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(venueMapper.writeValueAsString(null));
		}
		
		return ResponseEntity.status(HttpStatus.OK).body(venueMapper.writeValueAsString(venue));
	}

}
