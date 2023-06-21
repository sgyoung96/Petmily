package com.example.demo.email;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
@RestController
@CrossOrigin(origins = "*")

public class EmailController {
	
	@Autowired
	private EmailService emailService;

	@PostMapping("/emailConfirm")
	public Map emailConfirm(@RequestParam String email) throws Exception {
		Map map = new HashMap();
	  String confirm = emailService.sendSimpleMessage(email);
	 map.put("confirm", confirm);
	  return map;
	}
}
