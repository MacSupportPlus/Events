package com.macsupport.Repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.macsupport.Models.Event;

@Repository
public interface EventRepository extends CrudRepository<Event, Long> {
	List<Event>findAll();
}
