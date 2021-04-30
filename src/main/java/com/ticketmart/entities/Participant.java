package com.ticketmart.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonView;
import com.ticketmart.views.View;

@Entity
@Table(name = "participant")
public class Participant {
	
	private int idParticipant;
	private String name;
	private String description;
	
	private Set<Event> events;
	
	public Participant() {
		events = new HashSet<>();
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idParticipant")
	@JsonView(View.Detailed.class)
	public int getIdParticipant() {
		return idParticipant;
	}
	
	@Column(name = "name")
	@JsonView(View.Summary.class)
	public String getName() {
		return name;
	}
	
	@Column(name = "description")
	@JsonView(View.Detailed.class)
	public String getDescription() {
		return description;
	}
	
	@ManyToMany
	@JoinTable(name = "event_has_participant",
				joinColumns = @JoinColumn(name = "idParticipant"),
				inverseJoinColumns = @JoinColumn(name = "idEvent"))
	@JsonView(View.VeryDetailed.class)
	public Set<Event> getEvents() {
		return events;
	}
	
	// setter methods
	
	public void setIdParticipant(int idParticipant) {
		this.idParticipant = idParticipant;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}

	public void setEvents(Set<Event> events) {
		this.events = events;
	}
	
	@Override
	public String toString() {
		return "Participant - idParticipant: " + idParticipant + "  Name: " + name
				+ "  Description: " + description;
	}

}
