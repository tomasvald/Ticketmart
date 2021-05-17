package com.ticketmart.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.ticketmart.entities.Participant;
import com.ticketmart.service.ParticipantService;
import com.ticketmart.views.ParticipantListSerializer;
import com.ticketmart.views.ParticipantSerializer;

@RequestMapping("/participants")
@Controller
public class ParticipantsController {
	
	@Autowired
	private ParticipantService participantService;
	
	// objects for JSON serialization
	private ObjectMapper participantMapper;
	private SimpleModule participantModule;
	private ParticipantSerializer participantSerializer;	
	
	private ObjectMapper participantListMapper;
	private SimpleModule participantListModule;
	private ParticipantListSerializer participantListSerializer;
	
	public ParticipantsController() {
		this.participantMapper = new ObjectMapper();
		this.participantModule = new SimpleModule();
		this.participantSerializer = new ParticipantSerializer();
		
		this.participantModule.addSerializer(Participant.class, participantSerializer);
		this.participantMapper.registerModule(participantModule);
		
		this.participantListMapper = new ObjectMapper();
		this.participantListModule = new SimpleModule();
		this.participantListSerializer = new ParticipantListSerializer();
		
		this.participantListModule.addSerializer(Object.class, participantListSerializer);
		this.participantListMapper.registerModule(participantListModule);	
	}
	
	@RequestMapping(method = RequestMethod.GET, produces="application/json")
	public ResponseEntity<String> getParticipants() throws JsonProcessingException{
		List<Participant> participants = participantService.findAll();		
		return ResponseEntity.status(HttpStatus.OK)
							 .body(participantListMapper.writeValueAsString(participants));
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces="application/json")
	public ResponseEntity<String> getParticipant(@PathVariable Long id) throws JsonProcessingException {
		Participant participant = participantService.findById(id);
		
		if(participant == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
		
		return ResponseEntity.status(HttpStatus.OK).body(participantMapper.writeValueAsString(participant));
	}

}
