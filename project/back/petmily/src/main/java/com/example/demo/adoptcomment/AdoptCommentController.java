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
import org.springframework.web.bind.annotation.PutMapping;

/**
 * 1. 해당 게시글 댓글 전체 검색
 * 2. 댓글 입력
 * 3. 댓글 수정
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
		ArrayList<Adoptcomment> dtos = service.getAll(num);
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
	 * 댓글 수정
	 * @param dto
	 * @return
	 */
	@PutMapping("")
	public Map edit(AdoptCommentDto dto) {
		AdoptCommentDto adoptCommentDto = service.getComment(dto.getAb_num()); // 댓글 고유번호로 댓글 엔티티 조회
		AdoptCommentDto newDto = new AdoptCommentDto(adoptCommentDto.getAb_num(), adoptCommentDto.getContent(), adoptCommentDto.getId(), adoptCommentDto.getW_date(), adoptCommentDto.getNum());
		adoptCommentDto = service.edit(newDto);
		
		Map map = new HashMap();
		map.put("comment", adoptCommentDto);
		return map;
	}
	
	/**
	 * 댓글 삭제
	 * @param ab_num
	 */
	@DeleteMapping("")
	public void remove(int ab_num) {
		service.remove(ab_num);
	}
}