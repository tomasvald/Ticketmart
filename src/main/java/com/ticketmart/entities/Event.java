package com.ticketmart.entities;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

@Entity
@Table(name="event")
@NamedQueries(value = { 
	@NamedQuery(name = "Event.getEventsWithParticipant", 
				query = "select distinct e from Event e " +
						"left join fetch e.participants p"),
	@NamedQuery(name = "Event.getEventWithSections", 
				query = "select distinct e from Event e " +
						"left join fetch e.sections s " +
						"where e.id = :id")
})
public class Event {
	
	private int    idEvent;
	private String name;
	private String description;
	private Date   date;
	
	private Venue            venue;
	private Set<Participant> participants;
	private Set<Section>     sections;

	public Event() {
		participants = new HashSet<>();
		sections     = new HashSet<>();
	}
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="idEvent")
	public int getIdEvent() {
		return idEvent;
	}
	
	public void setIdEvent(int idEvent) {
		this.idEvent = idEvent;
	}
	
	@Column(name="name")
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	@Column(name="description")
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	@Temporal(TemporalType.DATE)
	@Column(name="date")
	public Date getDate() {
		return date;
	}
	
	public void setDate(Date date) {
		this.date = date;
	}
	
	@ManyToOne
	@JoinColumn(name="idVenue")
	public Venue getVenue() {
		return venue;
	}

	public void setVenue(Venue venue) {
		this.venue = venue;
	}

	@ManyToMany
	@JoinTable(name = "event_has_participant",
				joinColumns = @JoinColumn(name = "idEvent"),
				inverseJoinColumns = @JoinColumn(name = "idParticipant"))
	public Set<Participant> getParticipants() {
		return participants;
	}

	public void setParticipants(Set<Participant> participants) {
		this.participants = participants;
	}
	
	@OneToMany(mappedBy="event", cascade=CascadeType.ALL, orphanRemoval=true)
	public Set<Section> getSections() {
		return sections;
	}

	public void setSections(Set<Section> sections) {
		this.sections = sections;
	}
	
	@Override
	public String toString(){
		return "Event - idEvent: " + idEvent + " Name: " + name + " Description: " + description
				+ " Date: " + date;
	}

}
