package com.ticketmart.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ticketmart.entities.Status;
import com.ticketmart.entities.Ticket;
import com.ticketmart.repos.TicketRepository;

@Service("ticketService")
public class TicketServiceImpl implements TicketService {
	
	private static Logger logger = Logger.getLogger(TicketServiceImpl.class);
	
	@Autowired
	TicketRepository ticketRepository;
	@Autowired
	StatusService statusService;
	
	Status availableStatus;
	Status reservedStatus;
	Status soldStatus;
	
	@PostConstruct
	private void init() {
		availableStatus = statusService.findById(Status.AVAILABLE);
		reservedStatus = statusService.findById(Status.RESERVED);
		soldStatus = statusService.findById(Status.SOLD);
	}

	@Override
	public Ticket save(Ticket ticket) {
		ticketRepository.save(ticket);
		//logger.info("Ticket saved - idTicket: " + ticket.getIdTicket());
		
		return ticket;
	}

	@Override
	@Transactional(readOnly=true)
	public List<Ticket> findBySection(Long idSection) {
		return ticketRepository.findBySection(idSection);
	}

	@Override
	@Transactional(readOnly=true)
	public synchronized List<Ticket> findAvailable(Long idSection, int amountOfTickets) {
		List<Ticket> allAvailableTickets = ticketRepository.findAvailable(idSection);
		if(allAvailableTickets == null || allAvailableTickets.size() == 0) {
			return new ArrayList<Ticket>();
		}
		List<Ticket> reserve = reserveTickets(allAvailableTickets.subList(0, amountOfTickets));
		
		return reserve;
	}
	
	@Override
	public List<Ticket> findAvailableAndPurchase(Long idSection, int amountOfTickets){
		List<Ticket> ticketsFound = findAvailable(idSection, amountOfTickets);
		if(ticketsFound.size() == amountOfTickets) {
			List<Ticket> purchase = purchaseTickets(ticketsFound);
			return purchase;
		}
		cancelPurchase(ticketsFound);
		
		return null;
	}
	
	@Override
	public List<Ticket> purchaseTickets(List<Ticket> tickets){
		for(Ticket ticket : tickets) {
			ticket.setStatus(soldStatus);
			save(ticket);
		}
		logger.info(tickets.size() + " tickets sold");
		
		return tickets;
	}
	
	@Override
	public List<Ticket> reserveTickets(List<Ticket> tickets){
		for(Ticket ticket : tickets) {
			ticket.setStatus(reservedStatus);
			save(ticket);
		}
		logger.info(tickets.size() + " tickets reserved");
		
		return tickets;
	}

	@Override
	public List<Ticket> cancelPurchase(List<Ticket> tickets) {
		for(Ticket ticket : tickets) {
			ticket.setStatus(availableStatus);
			save(ticket);
		}
		logger.info(tickets.size() + " tickets cancelled");
		
		return tickets;
	}

}
