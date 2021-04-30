package com.ticketmart.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.annotation.JsonView;
import com.ticketmart.client.TicketRequest;
import com.ticketmart.entities.Ticket;
import com.ticketmart.service.DataService;
import com.ticketmart.views.View;

@RequestMapping("/tickets")
@Controller
public class TicketController {
	
	private DataService dataService;
	
	@RequestMapping(method = RequestMethod.GET, consumes="application/json", produces="application/json")
	@ResponseBody
	@JsonView(View.Summary.class)
	public List<Ticket> getTickets(@RequestBody TicketRequest request) {		
		return dataService.getTicketsAvailable(request.getIdSection(), 
												request.getAmountOfTickets());
	}
	
	// ------------------------------------------------
	
	@Autowired
	public void setDataService(DataService dataService) {
		this.dataService = dataService;
	}

}
