package com.ticketmart.views;

import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.ticketmart.entities.Ticket;

public class TicketListSerializer extends JsonSerializer<Object> {

	@Override
	public void serialize(
			Object value, 
			JsonGenerator gen, 
			SerializerProvider serializers) 
					throws IOException {
		
		@SuppressWarnings("unchecked")
		List<Ticket> tickets = (List<Ticket>)value;
		
		gen.writeStartArray();
			for(Ticket ticket : tickets) {
			gen.writeStartObject();
				gen.writeNumberField("idTicket", ticket.getIdTicket());
				gen.writeStringField("seatNumber", ticket.getSeatNumber());
			gen.writeEndObject();
			}
		gen.writeEndArray();
		
	}

}
