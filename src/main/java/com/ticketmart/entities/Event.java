package com.ticketmart.entities;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

@Entity
@Table(name="event")
public class Event {
	
	private int    idEvent;
	private String name;
	private String description;
	private Date   date;
	
	private Venue            venue;
	private Set<Participant> participants;
	private Set<Section>     sections;

	public Event() {
		this.participants = new HashSet<>();
		this.sections     = new HashSet<>();
	}
	
	// getter methods
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="idEvent")
	public int getIdEvent() {
		return idEvent;
	}
	
	@Column(name="name")
	public String getName() {
		return name;
	}
	
	@Column(name="description")
	public String getDescription() {
		return description;
	}
	
	@Temporal(TemporalType.DATE)
	@Column(name="date")
	public Date getDate() {
		return date;
	}
	
	@ManyToOne
	@JoinColumn(name="idVenue")
	public Venue getVenue() {
		return venue;
	}

	@ManyToMany
	@JoinTable(name = "event_has_participant",
				joinColumns = @JoinColumn(name = "idEvent"),
				inverseJoinColumns = @JoinColumn(name = "idParticipant"))
	@OrderBy(value = "name")
	public Set<Participant> getParticipants() {
		return participants;
	}
	
	@OneToMany(mappedBy="event", cascade=CascadeType.ALL, orphanRemoval=true)
	@OrderBy(value = "name")
	public Set<Section> getSections() {
		return sections;
	}
	
	// setter methods
	
	public void setIdEvent(int idEvent) {
		this.idEvent = idEvent;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public void setDate(Date date) {
		this.date = date;
	}

	public void setVenue(Venue venue) {
		this.venue = venue;
	}

	public void setParticipants(Set<Participant> participants) {
		this.participants = participants;
	}

	public void setSections(Set<Section> sections) {
		this.sections = sections;
	}
	
	//
	
	@Override
	public String toString(){
		return "Event - idEvent: " + idEvent + " Name: " + name + " Description: " + description
				+ " Date: " + date;
	}

}
