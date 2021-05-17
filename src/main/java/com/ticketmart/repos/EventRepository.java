package com.ticketmart.repos;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.EntityGraph.EntityGraphType;
import org.springframework.data.repository.CrudRepository;

import com.ticketmart.entities.Event;

public interface EventRepository extends CrudRepository<Event, Long> {
	@EntityGraph(value = "Event.detail", type = EntityGraphType.LOAD)
	Optional<Event>   findById(Long id);
	List<Event>       findByNameContainingIgnoreCase(String keyword);
	
}
