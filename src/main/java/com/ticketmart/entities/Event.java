package com.ticketmart.entities;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonView;
import com.ticketmart.views.View;

@Entity
@Table(name="event")
@NamedQueries(value = { 
	@NamedQuery(name = "Event.getEventDetailed", 
				query = "select distinct e from Event e " +
						"left join fetch e.participants p " +
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
		this.participants = new HashSet<>();
		this.sections     = new HashSet<>();
	}
	
	// getter methods
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="idEvent")
	@JsonView(View.Summary.class)
	public int getIdEvent() {
		return idEvent;
	}
	
	@Column(name="name")
	@JsonView(View.Summary.class)
	public String getName() {
		return name;
	}
	
	@Column(name="description")
	@JsonView(View.Detailed.class)
	public String getDescription() {
		return description;
	}
	
	@Temporal(TemporalType.DATE)
	@Column(name="date")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm")
	@JsonView(View.Summary.class)
	public Date getDate() {
		return date;
	}
	
	@ManyToOne
	@JoinColumn(name="idVenue")
	@JsonView(View.Summary.class)
	public Venue getVenue() {
		return venue;
	}

	@ManyToMany
	@JoinTable(name = "event_has_participant",
				joinColumns = @JoinColumn(name = "idEvent"),
				inverseJoinColumns = @JoinColumn(name = "idParticipant"))	
	@JsonView(View.Detailed.class)
	public Set<Participant> getParticipants() {
		return participants;
	}
	
	@OneToMany(mappedBy="event", cascade=CascadeType.ALL, orphanRemoval=true)
	@JsonView(View.Detailed.class)
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
