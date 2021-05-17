package com.ticketmart.repos;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.ticketmart.entities.Venue;

public interface VenueRepository extends CrudRepository<Venue, Long>{
	List<Venue> findByNameContainingIgnoreCase(String name);
}
