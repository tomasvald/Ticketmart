package com.ticketmart.repos;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.ticketmart.entities.Ticket;

public interface TicketRepository extends CrudRepository<Ticket, Long> {
	
	@Query("select t from Ticket t where t.section.idSection = :id")
	List<Ticket> findBySection(@Param("id") Long idSection);
	
	@Query("select t from Ticket t " +
			"where t.section.idSection = :id " +
			"and t.status.idStatus = 1")
	List<Ticket> findAvailable(@Param("id") Long idSection);
}
