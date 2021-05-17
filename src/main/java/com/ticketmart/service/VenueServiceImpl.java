package com.ticketmart.service;

import java.util.List;

import org.glassfish.jersey.internal.guava.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ticketmart.entities.Venue;
import com.ticketmart.repos.VenueRepository;

@Service("venueService")
public class VenueServiceImpl implements VenueService {
	
	@Autowired
	private VenueRepository venueRepository;

	@Override
	@Transactional(readOnly=true)
	public Venue findById(Long idVenue) {
		return venueRepository.findById(idVenue).orElse(null);
	}

	@Override
	@Transactional(readOnly=true)
	public List<Venue> findByName(String name) {
		return venueRepository.findByNameContainingIgnoreCase(name);
	}

	@Override
	@Transactional(readOnly=true)
	public List<Venue> findAll() {
		return Lists.newArrayList(venueRepository.findAll());
	}
	
}
