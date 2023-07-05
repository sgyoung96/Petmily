package com.example.demo.adoptliketable;

import java.util.HashMap;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins="*")
@RequestMapping("/adoptliketable")
public class AdoptliketableController {
	@Autowired
	private AdoptliketableService service;
	
	@GetMapping("/{id}/{num}")
	public Map getDuplicate(@PathVariable("id") String id, @PathVariable("num") int num) {
		Map map = new HashMap<>();
		boolean flag = true;
		AdoptliketableDto dto = service.getDuplicate(id, num);
		if(dto == null) {
			flag = true;
		}else {
			flag= false;
		}
		map.put("flag", flag);
		return map;
	}
	
	@PostMapping("")
	public Map addLike(AdoptliketableDto dto) {
		Map map = new HashMap<>();
		boolean flag = true;
		try {
			service.save(dto);
		}catch(Exception e) {
			flag = false;
		}
		map.put("flag", flag);
		return map;
	}
	
	@DeleteMapping("")
	public Map delLike(AdoptliketableDto dto) {
		Map map = new HashMap<>();
		boolean flag = true;
		try {
			AdoptliketableDto dto2 = service.getDuplicate(dto.getId().getId(), dto.getNum().getNum());
			dto.setDb_num(dto2.getDb_num());
			service.deleteOne(dto);
		}catch(Exception e) {
			flag = false;
		}
		map.put("flag", flag);
		return map;
	}
	
	@GetMapping("/id/{id}")
	public Map getWatch(@PathVariable("id") String id) {
		Map map = new HashMap<>();
		int dto = service.printPerson(id);
		map.put("dto", dto);
		return map;
	}
	
}
