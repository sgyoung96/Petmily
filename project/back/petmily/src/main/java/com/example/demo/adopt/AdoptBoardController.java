package com.example.demo.adopt;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins= "*")
@RequestMapping("/adaopt")
public class AdoptBoardController {
	@Autowired
	private AdoptBoardService service;
	
	@Value("${spring.servelt.multipart.location}")
	private String path;
	
	/**
	 * 전체 목록 검색
	 * @return
	 */
	@GetMapping("")
	public Map getAll() {
		ArrayList<AdoptBoardDto> list = service.getAll();
		Map map = new HashMap();
		map.put("list",  list);
		return map;
	}
	
	/**
	 * 게시글 상세 조회
	 * @param num
	 * @return
	 */
//	public Map getByNum(@PathVariable("num") int num) {
//		
//	}
	
}
