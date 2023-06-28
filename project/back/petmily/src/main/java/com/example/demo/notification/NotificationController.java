package com.example.demo.notification;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller(value="*")
public class NotificationController {
	
	@Autowired
	private NotificationService service = new NotificationService();
	
	public ArrayList<Notification> getAllNotification() {
		service.getAllNotification();
		return null;
	}
}
