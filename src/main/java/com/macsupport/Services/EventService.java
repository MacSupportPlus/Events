package com.macsupport.Services;

import java.util.List;
import java.util.Optional;

import com.macsupport.Models.Event;
import com.macsupport.Repositories.EventRepository;

public class EventService {
		
	private final EventRepository eventRepository;
	
	public EventService(EventRepository eventRepository) {
		this.eventRepository = eventRepository;
	}
	public Event addEvent(Event e) {
		return eventRepository.save(e);
	}
	public List<Event> getAll() {
		return eventRepository.findAll();
	} 
	public Event getEventById(Long id) {
		Optional<Event> event = eventRepository.findById(id);
		if(event.isPresent()) {
			return event.get();
		} else {
			return null;
		}
	}
}

