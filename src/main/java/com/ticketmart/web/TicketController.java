package com.ticketmart.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.ticketmart.entities.Ticket;
import com.ticketmart.service.TicketService;
import com.ticketmart.views.TicketListSerializer;

@RequestMapping("/tickets")
@Controller
public class TicketController {
	
	@Autowired
	private TicketService ticketService;
	
	// objects for JSON serialization
	private ObjectMapper ticketListMapper;
	private SimpleModule ticketListModule;
	private TicketListSerializer ticketListSerializer;
	
	public TicketController() {		
		this.ticketListMapper = new ObjectMapper();
		this.ticketListModule = new SimpleModule();
		this.ticketListSerializer = new TicketListSerializer();
		
		this.ticketListModule.addSerializer(Object.class, ticketListSerializer);
		this.ticketListMapper.registerModule(ticketListModule);	
	}
	
	@RequestMapping(method = RequestMethod.GET, produces="application/json")
	public ResponseEntity<String> getTickets(@RequestParam Long idSection, @RequestParam int amountOfTickets) throws JsonProcessingException {
		List<Ticket> tickets = ticketService.findAvailableAndPurchase(idSection, amountOfTickets);
		return ResponseEntity.status(HttpStatus.OK)
				 			 .body(ticketListMapper.writeValueAsString(tickets));
	}

}
