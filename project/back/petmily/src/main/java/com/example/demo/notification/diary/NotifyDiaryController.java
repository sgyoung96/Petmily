package com.example.demo.notification.diary;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 1. 전체 목록 검색
 * 2. 디테일 조회
 * 3. 글 추가
 * 5. 글 삭제
 * @author gayeong
 *
 */
@RestController
@CrossOrigin(origins= "*")
@RequestMapping("/notify")
public class NotifyDiaryController {
	@Autowired
	private NotifyDiaryService service;
	
	@GetMapping("/{id}")
	public HashMap<String, Object> getAll(@PathVariable("id") String id) {
		ArrayList<NotifyDiaryDto> list = new ArrayList<>();
		list = service.getAll(id);
		HashMap<String, Object> result = new HashMap<>();
		result.put("list", list);
		return result;
	}
}
