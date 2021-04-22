package com.ticketmart.api;

import javax.annotation.Resource;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.ticketmart.dao.DbOperationsDao;

@Path("/events") 
public class EventAPI {
	
	DbOperationsDao dbOperations;

	@GET
	@Path("/")
	@Produces(MediaType.TEXT_PLAIN)
	public String helloWorld() {
		return dbOperations.getEvents().toString();
	}
	
	// Bean
	
	public DbOperationsDao getDbOperations() {
		return dbOperations;
	}
	
	@Resource(name="dbOperations")
	public void setDbOperations(DbOperationsDao dbOperations) {
		this.dbOperations = dbOperations;
	}

}
