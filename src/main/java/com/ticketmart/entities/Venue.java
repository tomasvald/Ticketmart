package com.ticketmart.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonView;
import com.ticketmart.views.View;

@Entity
@Table(name = "venue")
public class Venue {
	
	private int    idVenue;
	private String name;
	private String address;
	private String city;
	private String country;
	
	private Set<Event> events;
	
	public Venue() {
		this.events = new HashSet<Event>();
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idVenue")
	@JsonView(View.Detailed.class)
	public int getIdVenue() {
		return idVenue;
	}
	
	@Column(name = "name")
	@JsonView(View.Summary.class)
	public String getName() {
		return name;
	}
	
	@Column(name = "address")
	@JsonView(View.Detailed.class)
	public String getAddress() {
		return address;
	}
	
	@Column(name = "city")
	@JsonView(View.Summary.class)
	public String getCity() {
		return city;
	}
	
	@Column(name = "country")
	@JsonView(View.Detailed.class)
	public String getCountry() {
		return country;
	}
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy="venue", cascade=CascadeType.ALL, orphanRemoval=true)
	@JsonView(View.VeryDetailed.class)
	public Set<Event> getEvents() {
		return events;
	}
	
	// setter methods
	
	public void setIdVenue(int idVenue) {
		this.idVenue = idVenue;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setAddress(String address) {
		this.address = address;
	}
	
	public void setCity(String city) {
		this.city = city;
	}
	
	public void setCountry(String country) {
		this.country = country;
	}

	public void setEvents(Set<Event> events) {
		this.events = events;
	}
	
	@Override
	public String toString() {
		return "Venue - Name: " + name + " City: " + city + " Country: " + country;
	}

}
