package com.ticketmart.repos;

import org.springframework.data.repository.CrudRepository;

import com.ticketmart.entities.Section;

public interface SectionRepository extends CrudRepository<Section, Long> {
	
}
