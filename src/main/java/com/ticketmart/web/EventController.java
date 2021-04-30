package com.ticketmart.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.annotation.JsonView;
import com.ticketmart.entities.Event;
import com.ticketmart.service.DataService;
import com.ticketmart.views.View;

@RequestMapping("/events")
@Controller
public class EventController {
	
	private DataService dataService;
	
	@RequestMapping(method=RequestMethod.GET, produces="application/json")
	@ResponseBody
	@JsonView(View.Summary.class)
	public List<Event> getEvents() {
		return dataService.getEvents();
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET, produces="application/json")
	@ResponseBody
	@JsonView(View.Detailed.class)
	public Event getEvent(@PathVariable int id) {
		return dataService.getEvent(id);
	}
	
	// ------------------------------------------------
	
	@Autowired
	public void setDataService(DataService dataService) {
		this.dataService = dataService;
	}

}
