package com.ticketmart.service;

import java.util.List;

import com.ticketmart.entities.Participant;

public interface ParticipantService {
	Participant       findById(Long idParticipant);
	List<Participant> findByName(String name);
	List<Participant> findAll();
}
