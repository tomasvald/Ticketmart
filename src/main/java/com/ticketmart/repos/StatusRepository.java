package com.ticketmart.repos;

import org.springframework.data.repository.CrudRepository;

import com.ticketmart.entities.Status;

public interface StatusRepository extends CrudRepository<Status, Long> {

}
