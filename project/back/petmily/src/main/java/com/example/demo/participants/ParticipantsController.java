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
import com.example.demo.volboard.VolboardService;
import com.example.demo.watchlist.WatchlistDto;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/participants")
public class ParticipantsController {
	@Autowired
	private ParticipantsService service;
	
	@Autowired
	private VolboardService service2;
	
	//봉사모집게시판에 신청된 사람의 인원수 출력
	@GetMapping("/boardnum/{boardnum}")
	public Map getCount(@PathVariable("boardnum") int boardnum) {
		int c = service.printPerson(boardnum);
		Map map = new HashMap();
		map.put("count", c);
		return map;
	}
	
	//봉사모집게시판에 신청된 사람의 인원명단 출력
	@GetMapping("/{boardnum}")
	public Map getPerson(@PathVariable("boardnum") int boardnum) {
		ArrayList<ParticipantsDto> list = service.getByNum(boardnum);
		Map map = new HashMap<>();
		if(list == null) {
			list = null;
		}
		map.put("list", list);
		return map;
	}
	
	//봉사신청 시 참가자테이블에 추가
	@PostMapping("")
	public Map addPerson(ParticipantsDto dto) {
		Member m = service.save(dto);
		service2.upCnt(dto.getBoardnum().getNum());
		Map map = new HashMap();
		map.put("member", m);
		return map;
	}
	
	//신청을 두 번하지 못하게 중복체크
	@GetMapping("/{id}/{num}")
	public Map checkUser(@PathVariable("id") String id, @PathVariable("num") int num) {
		ArrayList<ParticipantsDto> d = service.getByIdAndNum(id, num);
		Map map = new HashMap();
		map.put("list", d);
		return map;
	}
	
	//신청취소
	@DeleteMapping("")
	public Map delPerson2(String id, int boardnum) {
		boolean flag = true;
		service.delPerson(id, boardnum);
		service2.downCnt(boardnum);
		Map map = new HashMap();
		map.put("flag", flag);
		return map;
	}
	
	@GetMapping("/id2/{id}")
	public Map getParticipantsList(@PathVariable("id") String id) {
		Map map = new HashMap<>();
		ArrayList<ParticipantsDto> list = service.getById(id);
		map.put("list", list);
		return map;
	}
	
}
