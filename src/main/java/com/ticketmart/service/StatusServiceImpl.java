package com.ticketmart.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ticketmart.entities.Status;
import com.ticketmart.repos.StatusRepository;

@Service("statusService")
public class StatusServiceImpl implements StatusService {
	
	@Autowired
	StatusRepository statusRepository;

	@Override
	@Transactional(readOnly=true)
	public Status findById(Long idStatus) {
		return statusRepository.findById(idStatus).orElse(null);
	}

}
