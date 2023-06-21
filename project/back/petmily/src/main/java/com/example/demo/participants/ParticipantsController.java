package com.example.demo.participants;

import java.util.ArrayList;
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

import com.example.demo.member.Member;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/participants")
public class ParticipantsController {
	@Autowired
	private ParticipantsService service;
	
	@GetMapping("/boardnum/{boardnum}")
	public Map getCount(@PathVariable("boardnum") int boardnum) {
		int c = service.printPerson(boardnum);
		Map map = new HashMap();
		map.put("count", c);
		return map;
	}
	
	@PostMapping("")
	public Map addPerson(ParticipantsDto dto) {
		Member m = service.save(dto);
		Map map = new HashMap();
		map.put("member", m);
		return map;
	}
	
	@GetMapping("/{id}/{num}")
	public Map checkUser(@PathVariable("id") String id, @PathVariable("num") int num) {
		ArrayList<ParticipantsDto> d = service.getByIdAndNum(id, num);
		Map map = new HashMap();
		map.put("list", d);
		return map;
	}
	
	@DeleteMapping("")
	public Map delPerson2(String id, int boardnum) {
		boolean flag = true;
		service.delPerson(id, boardnum);
		Map map = new HashMap();
		map.put("flag", flag);
		return map;
	}
	
	
}
