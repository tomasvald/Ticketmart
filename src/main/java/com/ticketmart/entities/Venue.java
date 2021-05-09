package com.ticketmart.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

@Entity
@Table(name = "venue")
public class Venue {
	
	private int    idVenue;
	private String name;
	private String website;
	private String phone_number;
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
	public int getIdVenue() {
		return idVenue;
	}
	
	@Column(name = "name")
	public String getName() {
		return name;
	}
	
	@Column(name = "website")
	public String getWebsite() {
		return website;
	}
	
	@Column(name= "phone_number")
	public String getPhone_number() {
		return phone_number;
	}
	
	@Column(name = "address")
	public String getAddress() {
		return address;
	}
	
	@Column(name = "city")
	public String getCity() {
		return city;
	}
	
	@Column(name = "country")
	public String getCountry() {
		return country;
	}
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy="venue", cascade=CascadeType.ALL, orphanRemoval=true)
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

	public void setWebsite(String website) {
		this.website = website;
	}

	public void setPhone_number(String phone_number) {
		this.phone_number = phone_number;
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
		return "Venue - idVenue: " + idVenue + "Name: " + name + " City: " + city + 
				" Country: " + country;
	}

}
