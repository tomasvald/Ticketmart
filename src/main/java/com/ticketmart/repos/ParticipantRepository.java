package com.ticketmart.repos;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.EntityGraph.EntityGraphType;
import org.springframework.data.repository.CrudRepository;

import com.ticketmart.entities.Participant;

public interface ParticipantRepository extends CrudRepository<Participant, Long> {
	@EntityGraph(value = "Participant.detail", type = EntityGraphType.LOAD)
	Optional<Participant> findById(Long id);
	List<Participant>     findByNameContainingIgnoreCase(String name);

}
