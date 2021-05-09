package com.ticketmart.views;

import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.ticketmart.entities.Participant;

public class ParticipantListSerializer extends JsonSerializer<Object> {

	@Override
	public void serialize(
			Object value, 
			JsonGenerator gen, 
			SerializerProvider serializers) 
					throws IOException {
		
		@SuppressWarnings("unchecked")
		List<Participant> participants = (List<Participant>)value;
		
		gen.writeStartArray();
			for(Participant participant : participants) {
			gen.writeStartObject();
				gen.writeNumberField("idParticipant", participant.getIdParticipant());
				gen.writeStringField("name", participant.getName());
			gen.writeEndObject();
			}
		gen.writeEndArray();
		
	}

}
