package com.example.demo.adoptcomment;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdoptCommentService {
	
	@Autowired
	private AdoptCommentDao dao;
	
	/**
	 * 해당 게시글 댓글 전체 조회
	 * @param num
	 * @return
	 */
	public ArrayList<AdoptCommentDto> getAll(int num) {
		ArrayList<Adoptcomment> list = dao.findByNum(num);
		ArrayList<AdoptCommentDto> list2 = new ArrayList<AdoptCommentDto>();
		
		for (Adoptcomment adoptComment : list) {
			list2.add(new AdoptCommentDto(adoptComment.getAb_num(), adoptComment.getContent(), adoptComment.getId(), adoptComment.getW_date(), adoptComment.getNum()));
		}
		return list2;
	}
	
	/**
	 * 댓글 입력
	 * @param dtoz
	 * @return
	 */
	public Adoptcomment add(AdoptCommentDto dto) {
		Adoptcomment adoptComment = (Adoptcomment) dao.save(new AdoptCommentDto(dto.getAb_num(), dto.getContent(), dto.getId(), dto.getW_date(), dto.getNum()));
		return adoptComment;
	}
	
	/**
	 * 특정 댓글 조회
	 * @param dto
	 * @return
	 */
	public AdoptCommentDto getComment(int ab_num) {
		AdoptCommentDto dto = new AdoptCommentDto();
		dto = dao.findByAb_num(ab_num);
		return dto;
	}
	/**
	 * 댓글 삭제
	 * @param num
	 */
	public void remove(int num) {
		dao.deleteByAb_num(num);
	}
}