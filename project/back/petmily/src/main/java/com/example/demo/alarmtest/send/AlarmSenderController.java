package com.example.demo.alarmtest.send;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

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
@RequestMapping("/alarm")
public class AlarmSenderController {
	@Autowired
	private AlarmSenderService service;
	
	/**
	 * 저장
	 * @param dto
	 * @return
	 */
	@PostMapping("/sender")
	public Map add(AlarmSenderDto dto) {
		boolean flag = true;
		Alarmsender alarmsender = new Alarmsender(); 
		try {
			alarmsender = service.add(dto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		Map map = new HashMap();
		map.put("flag", flag);
		map.put("dto", dto);
		map.put("obj", alarmsender);
		return map;
	}
}
