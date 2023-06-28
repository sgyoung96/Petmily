package com.example.demo.liketable;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins="*")
@RequestMapping("/liketable")
public class LiketableController {
	@Autowired
	private LiketableService service;
	
	@GetMapping("/{id}/{num}")
	public Map getDuplicate(@PathVariable("id") String id, @PathVariable("num") int num) {
		Map map = new HashMap<>();
		boolean flag = true;
		LiketableDto dto = service.getDuplicate(id, num);
		if(dto == null) {
			flag = true;
		}else {
			flag= false;
		}
		map.put("flag", flag);
		return map;
	}
}
