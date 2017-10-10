package com.demo.rest;

import java.security.Principal;
import java.sql.Time;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
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
	
	@RequestMapping(value = "/addevent", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody String addevent(@ModelAttribute("eventForm") SimpleEvent event, Model model, Principal principal) {
		User user = userRepository.findByUsername(principal.getName());
		Event event2 = event.getEvent();
		event2.setUser(user);
		eventRepository.save(event2);
		return "{\"event\" : \"Event "+ event2.getDescription().substring(0, 10) + "... added\"}";
	}
//	@RequestMapping(value = "/addevent", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
//	public @ResponseBody Greeter greeter(@RequestParam(value="name", defaultValue="Word") String name) {
//		return new Greeter(atomicLong.incrementAndGet(), String.format(txt, name));
//	}
}
