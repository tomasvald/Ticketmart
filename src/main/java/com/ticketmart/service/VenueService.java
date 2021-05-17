package com.ticketmart.service;

import java.util.List;

import com.ticketmart.entities.Venue;

public interface VenueService {
	Venue             findById(Long idVenue);
	List<Venue>       findByName(String name);
	List<Venue>       findAll();
}
