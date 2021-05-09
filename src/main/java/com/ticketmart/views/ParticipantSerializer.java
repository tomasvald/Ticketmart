package com.ticketmart.views;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.ticketmart.entities.Event;
import com.ticketmart.entities.Participant;

public class ParticipantSerializer extends JsonSerializer<Participant> {

	@Override
	public void serialize(
			Participant participant, 
			JsonGenerator gen, 
			SerializerProvider serializers) 
					throws IOException {
		
		gen.writeStartObject();
			gen.writeNumberField("idParticipant", participant.getIdParticipant());
			gen.writeStringField("name", participant.getName());
			gen.writeStringField("description", participant.getDescription());
			gen.writeArrayFieldStart("events");
				for(Event event : participant.getEvents()) {
				gen.writeStartObject();
				gen.writeNumberField("idEvent", event.getIdEvent());
				gen.writeStringField("name", event.getName());
				gen.writeStringField("date", event.getDate().toString());
					gen.writeObjectFieldStart("venue");
						gen.writeNumberField("idVenue", event.getVenue().getIdVenue());
						gen.writeStringField("name", event.getVenue().getName());
						gen.writeStringField("city", event.getVenue().getCity());
					gen.writeEndObject();
				gen.writeEndObject();
				}
			gen.writeEndArray();
		gen.writeEndObject();
		
	}

}
