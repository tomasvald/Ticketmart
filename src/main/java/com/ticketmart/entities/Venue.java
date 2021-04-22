package com.ticketmart.entities;

import java.util.Set;

import javax.persistence.*;

@Entity
@Table(name = "venue")
public class Venue {
	
	private int    idVenue;
	private String name;
	private String address;
	private String city;
	private String country;
	
	private Set<Event> events;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idVenue")
	public int getIdVenue() {
		return idVenue;
	}
	
	public void setIdVenue(int idVenue) {
		this.idVenue = idVenue;
	}
	
	@Column(name = "name")
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	@Column(name = "address")
	public String getAddress() {
		return address;
	}
	
	public void setAddress(String address) {
		this.address = address;
	}
	
	@Column(name = "city")
	public String getCity() {
		return city;
	}
	
	public void setCity(String city) {
		this.city = city;
	}
	
	@Column(name = "country")
	public String getCountry() {
		return country;
	}
	
	public void setCountry(String country) {
		this.country = country;
	}
	
	@OneToMany(mappedBy="venue", cascade=CascadeType.ALL, orphanRemoval=true)
	public Set<Event> getEvents() {
		return events;
	}

	public void setEvents(Set<Event> events) {
		this.events = events;
	}
	
	@Override
	public String toString() {
		return "Venue - Name: " + name + " City: " + city + " Country: " + country;
	}

}
