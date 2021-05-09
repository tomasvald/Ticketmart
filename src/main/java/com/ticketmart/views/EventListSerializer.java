package com.ticketmart.views;

import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.ticketmart.entities.Event;

public class EventListSerializer extends JsonSerializer<Object>{

	@Override
	public void serialize(
			Object value, 
			JsonGenerator gen, 
			SerializerProvider serializers) 
					throws IOException {
		
		@SuppressWarnings("unchecked")
		List<Event> events = (List<Event>)value;
		
		gen.writeStartArray();
			for(Event event : events) {
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
	}

}
