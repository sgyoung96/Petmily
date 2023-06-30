package com.example.demo.applyform;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController // rest api controller
@CrossOrigin(origins = "*") // 모든 ip로부터 요청 받기 허용
@RequestMapping("/Applyform")
public class ApplyformController {
	@Autowired
	private ApplyformService service;
	
	// 추가
	@PostMapping("")
	public Map add(ApplyformDto dto) {
		System.out.println("id : " + dto.getId() + "num : " + dto.getNum());
		ApplyformDto d = service.save(dto);
		Map map = new HashMap();
		map.put("dto", d);
		return map;
	}
	
	// 전체목록 검색
	@GetMapping("")
	public Map getAll() {
		ArrayList<ApplyformDto> list = service.getAll();
		Map map = new HashMap();
		map.put("list", list);
		return map;
	}
		
	//id로 검색
	@GetMapping("/getbyid")
	public Map getbyid(String id) {
		ArrayList<ApplyformDto> list = service.findByMemberId(id);
		Map map = new HashMap();
		map.put("list", list);
		return map;
	}
	
	//check로 검색
		@GetMapping("/getbycheck")
		public Map getbycheck(int check) {
			ArrayList<ApplyformDto> list = service.findByCheck(check);
			Map map = new HashMap();
			map.put("list", list);
			return map;
		}
	
	//승인
	@PatchMapping("/apply/{num}")
	public Map apply(@PathVariable("num") int num) {
		boolean flag = true;
		try {
			service.apply(num);
		} catch (Exception e) {
			flag = false;
		}
		Map map = new HashMap();
		map.put("flag", flag);
		return map;
	}	
	
	//거부
		@PatchMapping("/refuse/{num}")
		public Map refuse(@PathVariable("num") int num) {
			boolean flag = true;
			try {
				service.refuse(num);
			} catch (Exception e) {
				flag = false;
			}
			Map map = new HashMap();
			map.put("flag", flag);
			return map;
		}	
		
	//삭제
	@DeleteMapping("/{num}")
	public void delform(@PathVariable("num") int num) {
		service.delApplyform(num);
	}
	
}
