package com.ticketmart.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonView;
import com.ticketmart.views.View;

@Entity
@Table(name="section")
@NamedQueries(value = { 
	@NamedQuery(name = "Section.getSectionWithTickets", 
				query = "select distinct s from Section s " +
						"left join fetch s.tickets t " +
						"where s.idSection = :idSection " +
						"order by s.name"),
	@NamedQuery(name = "Section.getSectionWithAvailableTickets", 
				query = "select distinct s from Section s " +
						"left join fetch s.tickets t " +
						"where s.idSection = :idSection and t.status = 1" +
						"order by s.name") 
})
public class Section {
	
	private int    idSection;
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
	@JsonView(View.Summary.class)
	public int getIdSection() {
		return idSection;
	}
	
	@Column(name="name")
	@JsonView(View.Summary.class)
	public String getName() {
		return name;
	}
	
	@Column(name="totalCapacity")
	@JsonView(View.VeryDetailed.class)
	public int getTotalCapacity() {
		return totalCapacity;
	}
	
	@Column(name="cost")
	@JsonView(View.Detailed.class)
	public float getCost() {
		return cost;
	}
	
	@ManyToOne
	@JoinColumn(name="idEvent")
	@JsonView(View.VeryDetailed.class)
	public Event getEvent() {
		return event;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy="section", cascade=CascadeType.ALL, orphanRemoval=true)
	@JsonView(View.VeryDetailed.class)
	public Set<Ticket> getTickets() {
		return tickets;
	}
	
	// setter methods
	
	public void setIdSection(int idSection) {
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
