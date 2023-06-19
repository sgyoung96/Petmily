package com.example.demo.participants;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/volboard")
public class ParticipantsController {
	@Autowired
	private ParticipantsService service;
	
	@GetMapping("/num/{num}")
	public Map getCount(@PathVariable("num") int num) {
		long c = service.printPerson(num);
		Map map = new HashMap();
		map.put("count", c);
		return map;
	}
	
	
}
