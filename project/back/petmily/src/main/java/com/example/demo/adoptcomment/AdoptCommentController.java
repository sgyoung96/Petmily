package com.example.demo.adoptcomment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * 1. 해당 게시글 댓글 전체 검색
 * 2. 댓글 입력
 * 4. 댓글 삭제 
 * @author gayeong
 *
 */
@Controller
public class AdoptCommentController {
	
	@Autowired
	private AdoptCommentService service;
	
	/**
	 * 해당 게시글 댓글 전체 검색
	 * @return
	 */
	@GetMapping("/{num}")
	public Map getAll(@PathVariable("num") int num) {
		ArrayList<AdoptCommentDto> dtos = service.getAll(num);
		Map map = new HashMap();
		map.put("dto", dtos);
		return map;
	}
	
	/**
	 * 댓글 입력
	 * @param dto
	 * @return
	 */
	@PostMapping("")
	public Map add(AdoptCommentDto dto) {
		Adoptcomment adoptComment = service.add(dto);
		Map map = new HashMap();
		map.put("adoptComment", adoptComment);
		return map;
	}
	
	/**
	 * 댓글 삭제
	 * @param ab_num
	 */
	@DeleteMapping("/{num}")
	public Map remove(@PathVariable("num") int ab_num) {
		Map map = new HashMap();
		AdoptCommentDto dto2 = null;
		boolean flag = true;
		try {
			service.remove(ab_num);
		} catch (Exception e) {
			flag = false;
		}
		map.put("flag", flag);
		return map;
	}
}