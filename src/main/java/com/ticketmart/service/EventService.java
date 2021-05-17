package com.ticketmart.service;

import java.util.List;

import com.ticketmart.entities.Event;

public interface EventService {
	List<Event>       findByName(String name);
	Event             findById(Long idEvent);
	List<Event>       findAll();

}
