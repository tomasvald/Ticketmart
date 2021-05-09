package com.ticketmart.views;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.ticketmart.entities.Venue;

public class VenueSerializer extends JsonSerializer<Venue> {

	@Override
	public void serialize(Venue value, 
							JsonGenerator gen, 
							SerializerProvider provider) 
							throws IOException {
		
		gen.writeStartObject();
		gen.writeNumberField("id",           value.getIdVenue());
		gen.writeStringField("name",         value.getName());
		gen.writeStringField("address",      value.getAddress());
		gen.writeStringField("website",      value.getWebsite());
		gen.writeStringField("phone_number", value.getPhone_number());
		gen.writeStringField("city",         value.getCity());
		gen.writeStringField("country",      value.getCountry());
		gen.writeEndObject();
		
	}

}
