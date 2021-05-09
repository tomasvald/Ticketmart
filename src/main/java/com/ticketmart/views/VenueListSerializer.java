package com.ticketmart.views;

import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.ticketmart.entities.Venue;

public class VenueListSerializer extends JsonSerializer<Object>{

	@Override
	public void serialize(Object value, 
			JsonGenerator gen, 
			SerializerProvider serializers) 
					throws IOException {
		
		@SuppressWarnings("unchecked")
		List<Venue> venues = (List<Venue>)value;
		
		gen.writeStartArray();
		for(Venue venue : venues) {
			gen.writeStartObject();
			gen.writeNumberField("id", venue.getIdVenue());
			gen.writeStringField("name", venue.getName());
			gen.writeStringField("city", venue.getCity());
			gen.writeStringField("country", venue.getCountry());
			gen.writeEndObject();
		}
		gen.writeEndArray();
		
	}

}
