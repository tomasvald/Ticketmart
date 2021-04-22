package com.ticketmart.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

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
	public int getIdParticipant() {
		return idParticipant;
	}
	
	public void setIdParticipant(int idParticipant) {
		this.idParticipant = idParticipant;
	}
	
	@Column(name = "name")
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	@Column(name = "description")
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	@ManyToMany
	@JoinTable(name = "event_has_participant",
				joinColumns = @JoinColumn(name = "idParticipant"),
				inverseJoinColumns = @JoinColumn(name = "idEvent"))
	public Set<Event> getEvents() {
		return events;
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
