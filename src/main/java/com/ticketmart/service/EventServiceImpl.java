package com.ticketmart.service;

import java.util.List;

import org.glassfish.jersey.internal.guava.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ticketmart.entities.Event;
import com.ticketmart.repos.EventRepository;

@Service("eventService")
public class EventServiceImpl implements EventService {
	
	@Autowired
	private EventRepository eventRepository;

	@Override
	@Transactional(readOnly=true)
	public List<Event> findByName(String name) {
		return eventRepository.findByNameContainingIgnoreCase(name);
	}
	
	@Override
	@Transactional(readOnly=true)
	public Event findById(Long idEvent) {
		return eventRepository.findById(idEvent).orElse(null);
	}

	@Override
	@Transactional(readOnly=true)
	public List<Event> findAll() {
		return Lists.newArrayList(eventRepository.findAll());
	}

}
