package com.demo.rest;

import java.security.Principal;
import java.sql.Time;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.demo.rest.dao.EventRepository;
import com.demo.rest.dao.UserRepository;
import com.demo.rest.model.Event;
import com.demo.rest.model.SimpleEvent;
import com.demo.rest.model.User;

@RestController
@RequestMapping("/rest")
public class RestCont {

	@Autowired
	private EventRepository eventRepository;
	@Autowired
	private UserRepository userRepository;
	AtomicLong atomicLong = new AtomicLong();

	@RequestMapping(value = "/addevent", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody String addevent(@ModelAttribute("eventForm") SimpleEvent event, Model model,
			Principal principal) {
		System.out.println("ADDDDDING");
		User user = userRepository.findByUsername(principal.getName());
		Event event2 = event.getEvent();
		event2.setUser(user);
		eventRepository.save(event2);
		return "{\"event\" : \"Event " + event2.getDescription().substring(0, 10) + "... added\"}";
	}

	@RequestMapping(value = "/getevents", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ArrayList<Event> getEvents(@RequestParam(value = "tag", defaultValue = "", required = false) String tag,
			Principal principal) {
		Long id = userRepository.findIdOfUserByName(principal.getName());
		System.out.println("USER ID " + id);
		ArrayList<Event> events = new ArrayList<>();
		if (tag.isEmpty())
			events.addAll(eventRepository.findByUserId(id));
		else
			events.addAll(eventRepository.findByTagAndUser(id, tag));
		System.out.println("IS EMTY " + events.isEmpty());
		return events;
	}
}
