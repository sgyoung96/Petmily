package com.example.demo.diarycomment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins="*")
@RequestMapping("/dcomment")
public class DiarycommentController {
	@Autowired
	private DiarycommentService service;
	
	//추가 수정
	@PostMapping("")
	public Map addComment(DiarycommentDto dto) {
		Map map = new HashMap();
		DiarycommentDto dto2 = null;
		boolean flag = true;
		try {
			dto2 = service.save(dto);
		} catch (Exception e) {
			flag = false;
		}
		map.put("flag", flag);
		map.put("dto", dto2);
		return map;
	}
	
	@GetMapping("/{num}")
	public Map getByNum(@PathVariable("num") int num) {
		Map map = new HashMap();
		ArrayList<DiarycommentDto> dto = null;
		boolean flag = true;
		try {
			dto = service.getByNum(num);
		} catch (Exception e) {
			flag = false;
		}
		map.put("flag", flag);
		map.put("dto", dto);
		return map;
	}
	
	@PutMapping("")
	public Map edit(DiarycommentDto dto) {
		Map map = new HashMap();
		DiarycommentDto dto2 = null;
		boolean flag = true;
		try {
			dto2 = service.save(dto);
		} catch (Exception e) {
			flag = false;
		}
		map.put("flag", flag);
		map.put("dto", dto2);
		return map;
	}
	
	@DeleteMapping("/{num}")
	public Map delete(@PathVariable("num") int num) {
		Map map = new HashMap();
		DiarycommentDto dto2 = null;
		boolean flag = true;
		try {
			service.delDiarycomment(num);
		} catch (Exception e) {
			flag = false;
		}
		map.put("flag", flag);
		return map;
	}
	}

