package com.ticketmart.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ticketmart.entities.Section;
import com.ticketmart.repos.SectionRepository;

@Service("sectionService")
public class SectionServiceImpl implements SectionService {
	
	@Autowired
	SectionRepository sectionRepository;

	@Override
	@Transactional(readOnly=true)
	public Section findById(Long idSection) {
		return sectionRepository.findById(idSection).orElse(null);
	}

}
