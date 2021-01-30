package com.mystartup.calendar;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

//@RestController
@Controller
@RequestMapping(path = "/event")
public class EventController {
	@Autowired
	private EventRepository eventRepository;

	private static final String template = "Hello, %s";
	private final AtomicLong counter = new AtomicLong();

	@GetMapping(path = "")
	public @ResponseBody Iterable<Event> getAllEvents() {
		return eventRepository.findAll();
	}
	
	@GetMapping(path = "/{id}")
	public @ResponseBody Optional<Event> getEvent(@PathVariable ("id") final long id) {
		return eventRepository.findById(id);
	}
	
	@PostMapping("") 
	public ResponseEntity<Event> addEvent(@RequestBody Event event) {
		event = eventRepository.save(event);
		return new ResponseEntity<Event>(event, HttpStatus.CREATED);
	}
	
	@DeleteMapping(path = "/{id}") 
	public @ResponseBody String removeEvent(@PathVariable ("id") final long id) {
		eventRepository.deleteById(id);
		return "Deleted";
	}
	
	@PutMapping("")
	public @ResponseBody Event updateEvent(@RequestBody Event event) {
		event = eventRepository.save(event);
		return event;
	}
}
