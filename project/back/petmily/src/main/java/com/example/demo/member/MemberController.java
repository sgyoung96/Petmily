package com.example.demo.member;

import java.util.HashMap;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/members")
public class MemberController {
	@Autowired
	private MemberService service;
	
//	@Autowired
//	private JwtTokenProvider tokenprovider;// 멤버변수로 토큰 브로바이더 추가
	

	// 가입
	@PostMapping("")
	public Map join(MemberDto dto) {
		MemberDto d = service.save(dto);
		Map map = new HashMap();
		map.put("dto", d);
		return map;
	}
	
	// idcheck 검색
	@GetMapping("/{id}")
	public Map get(@PathVariable("id") String id) {

		Map map = new HashMap();
		MemberDto d = service.getMember(id);
		map.put("dto", d);

		return map;
	}
	
	

}
