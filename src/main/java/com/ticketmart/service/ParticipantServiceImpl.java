package com.ticketmart.service;

import java.util.List;

import org.glassfish.jersey.internal.guava.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ticketmart.entities.Participant;
import com.ticketmart.repos.ParticipantRepository;

@Service("participantService")
public class ParticipantServiceImpl implements ParticipantService {
	
	@Autowired
	ParticipantRepository participantRepository;

	@Override
	@Transactional(readOnly=true)
	public Participant findById(Long idParticipant) {
		return participantRepository.findById(idParticipant).orElse(null);
	}

	@Override
	@Transactional(readOnly=true)
	public List<Participant> findByName(String name) {
		return participantRepository.findByNameContainingIgnoreCase(name);
	}

	@Override
	@Transactional(readOnly=true)
	public List<Participant> findAll() {
		return Lists.newArrayList(participantRepository.findAll());
	}

}
