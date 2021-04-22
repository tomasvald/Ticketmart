package com.ticketmart.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

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
						"where s.idSection = :idSection and t.status = 1") 
})
public class Section {
	
	private int idSection;
	private String name;
	private int totalCapacity;
	private float cost;
	
	private Event event;
	private Set<Ticket> tickets;
	
	public Section() {
		tickets = new HashSet<>();
	}

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="idSection")
	public int getIdSection() {
		return idSection;
	}
	
	public void setIdSection(int idSection) {
		this.idSection = idSection;
	}
	
	@Column(name="name")
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	@Column(name="totalCapacity")
	public int getTotalCapacity() {
		return totalCapacity;
	}
	
	public void setTotalCapacity(int totalCapacity) {
		this.totalCapacity = totalCapacity;
	}
	
	@Column(name="cost")
	public float getCost() {
		return cost;
	}
	
	public void setCost(float cost) {
		this.cost = cost;
	}
	
	@ManyToOne
	@JoinColumn(name="idEvent")
	public Event getEvent() {
		return event;
	}

	public void setEvent(Event event) {
		this.event = event;
	}

	@OneToMany(mappedBy="section", cascade=CascadeType.ALL, orphanRemoval=true)
	public Set<Ticket> getTickets() {
		return tickets;
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
