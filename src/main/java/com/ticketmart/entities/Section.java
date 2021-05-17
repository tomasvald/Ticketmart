package com.ticketmart.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

@Entity
@Table(name="section")
public class Section {
	
	private Long   idSection;
	private String name;
	private int    totalCapacity;
	private float  cost;
	
	private Event       event;
	private Set<Ticket> tickets;
	
	public Section() {
		tickets = new HashSet<>();
	}
	
	// getter methods

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="idSection")
	public Long getIdSection() {
		return idSection;
	}
	
	@Column(name="name")
	public String getName() {
		return name;
	}
	
	@Column(name="totalCapacity")
	public int getTotalCapacity() {
		return totalCapacity;
	}
	
	@Column(name="cost")
	public float getCost() {
		return cost;
	}
	
	@ManyToOne
	@JoinColumn(name="idEvent")
	public Event getEvent() {
		return event;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy="section", cascade=CascadeType.ALL, orphanRemoval=true)
	public Set<Ticket> getTickets() {
		return tickets;
	}
	
	// setter methods
	
	public void setIdSection(Long idSection) {
		this.idSection = idSection;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setTotalCapacity(int totalCapacity) {
		this.totalCapacity = totalCapacity;
	}
	
	public void setCost(float cost) {
		this.cost = cost;
	}

	public void setEvent(Event event) {
		this.event = event;
	}

	public void setTickets(Set<Ticket> tickets) {
		this.tickets = tickets;
	}
	
	@Override
	public String toString(){
		return "Section - idSection: " + idSection + " Name: " + name 
				+" Total Capacity: " + totalCapacity + " Cost: " + cost;
	}

}
