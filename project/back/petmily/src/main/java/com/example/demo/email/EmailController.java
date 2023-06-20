package com.example.demo.email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/email")
public class EmailController {
	
	@Autowired
	private EmailService emailService;

	@PostMapping("/emailConfirm")
	public String emailConfirm(@RequestParam String email) throws Exception {

	  String confirm = emailService.sendSimpleMessage(email);

	  return confirm;
	}
}
