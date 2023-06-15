package com.example.demo.diarycomment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins="*")
@RequestMapping("/dcomment")
public class DiarycommentController {
	@Autowired
	private DiarycommentService service;
	
	//댓글리스트
	@GetMapping
	public Map getByNum() {
		ArrayList<DiarycommentDto> list = service.getByNum(num);
		Map map = new HashMap();
		map.put("list", list);
		return map;
	}
}
