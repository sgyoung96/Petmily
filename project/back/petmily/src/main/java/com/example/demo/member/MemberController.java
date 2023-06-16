package com.example.demo.member;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.auth.JwtTokenProvider;



@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/members")
public class MemberController {
	@Autowired
	private MemberService service;
	
	@Autowired
	private JwtTokenProvider tokenprovider;// 멤버변수로 토큰 브로바이더 추가
	

	// 가입
	@PostMapping("")
	public Map join(MemberDto dto) {
		MemberDto d = service.save(dto);
		Map map = new HashMap();
		map.put("dto", d);
		return map;
	}
	
	//==================== token ======================
	
	
	// idcheck 검색
	// 검색
	@GetMapping("/{id}")
	public Map get(@PathVariable("id") String id, @RequestHeader(name = "token", required = false) String token) {

		Map map = new HashMap();

		if (token != null) { // 로그인 후
			try {
				String id2 = tokenprovider.getUsernameFromToken(token);
				if (!id.equals(id2)) {
					map.put("dto", null);
					return map;
				}
			} catch (Exception e) {
				map.put("dto", null);
				return map;
			}
		}
		MemberDto d = service.getMember(id);
		map.put("dto", d);

		return map;
		}
	
	
	
	//로그인
	@PostMapping("/login")
	public Map login(String id, String pwd) {
		Map map = new HashMap();
		boolean flag = false;
		MemberDto dto = service.getMember(id);
		if (dto != null && pwd.equals(dto.getPwd())) {
			String token = tokenprovider.generateJwtToken(dto); // 토큰 생성
			flag = true;
			map.put("token", token);
		}
		map.put("flag", flag);
		return map;
	}
	
	//검색
	@PostMapping("/token")
	public Map getByToken(String token) {
		boolean flag = true;
		Map map = new HashMap();
		try {
			// 받은 토큰에서 로그인한 사람의 아이디, 타입 정보를 추출
			String id = tokenprovider.getUsernameFromToken(token);
			map.put("id", id);
		} catch (Exception e) {
			flag = false;
		}
		map.put("flag", flag);
		return map;
	}
	
	// 검색
	@GetMapping("")
	public Map getInfo(@RequestHeader("token") String token) { // @RequestHeader("token") : 요청 헤더 객체 받아오기
		boolean flag = true;
		Map map = new HashMap();
		try {
			String id = tokenprovider.getUsernameFromToken(token);
			map.put("id", id);
		} catch (Exception e) {
			flag = false;
		}
		map.put("flag", flag);
		return map;
		}
	
	// ======================================================
	
	//내정보 수정
	@PutMapping("")
	public Map edit(MemberDto dto) {
		MemberDto old = service.getMember(dto.getId());
		old.setEmail(dto.getEmail());
		old.setPwd(dto.getPwd());
		old.setPhone(dto.getPhone());
		
		MemberDto d = service.save(old);
		Map map = new HashMap();
		map.put("dto",d);
		return map;
	}
	
	//탈퇴
	@DeleteMapping("/{id}")
	public Map del(@PathVariable("id") String id) {

		boolean flag = true;
		try {
			service.delMember(id);
		} catch (Exception e) {
			flag = false;
		}
		Map map = new HashMap();
		map.put("flag", flag);
		return map;
	}
	

}
