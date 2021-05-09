package com.ticketmart.views;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.ticketmart.entities.Event;
import com.ticketmart.entities.Participant;
import com.ticketmart.entities.Section;

public class EventSerializer extends JsonSerializer<Event> {

	@Override
	public void serialize(
			Event value, JsonGenerator gen, 
			SerializerProvider serializers) 
					throws IOException {
		
		gen.writeStartObject();
			gen.writeNumberField("idEvent", value.getIdEvent());
			gen.writeStringField("name", value.getName());
			gen.writeStringField("description", value.getDescription());
			gen.writeStringField("date", value.getDate().toString());
			gen.writeObjectFieldStart("venue");
				gen.writeNumberField("idVenue", value.getVenue().getIdVenue());
				gen.writeStringField("name", value.getVenue().getName());
				gen.writeStringField("address", value.getVenue().getAddress());
				gen.writeStringField("city", value.getVenue().getCity());
				gen.writeStringField("country", value.getVenue().getCountry());
			gen.writeEndObject();
			gen.writeArrayFieldStart("participants");
				for(Participant participant : value.getParticipants()) {
				gen.writeStartObject();
				gen.writeNumberField("idParticipant", participant.getIdParticipant());
				gen.writeStringField("name", participant.getName());
				gen.writeStringField("description", participant.getDescription());
				gen.writeEndObject();
				}
			gen.writeEndArray();
			gen.writeArrayFieldStart("sections");
				for(Section section : value.getSections()) {
				gen.writeStartObject();
				gen.writeNumberField("idSection", section.getIdSection());
				gen.writeStringField("name", section.getName());
				gen.writeNumberField("cost", section.getCost());
				gen.writeEndObject();
				}
			gen.writeEndArray();
		gen.writeEndObject();
	}

}
