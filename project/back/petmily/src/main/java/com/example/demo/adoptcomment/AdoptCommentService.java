package com.example.demo.adoptcomment;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;

public class AdoptCommentService {
	
	@Autowired
	private AdoptCommentDao dao;
	
	/**
	 * 해당 게시글 댓글 전체 조회
	 * @param num
	 * @return
	 */
	public ArrayList<Adoptcomment> getAll(int num) {
		ArrayList<Adoptcomment> list = dao.getAll(num);
		ArrayList<Adoptcomment> list2 = new ArrayList<Adoptcomment>();
		
		for (int i = 0; i < list.size(); i++) {
			list2.add(new Adoptcomment(list.get(i).getAb_num(), list.get(i).getContent(), list.get(i).getId(), list.get(i).getW_date(), list.get(i).getNum()));
		}
		return list2;
	}
	
	/**
	 * 댓글 입력
	 * @param dtoz
	 * @return
	 */
	public Adoptcomment add(AdoptCommentDto dto) {
		Adoptcomment adoptComment = dao.add(new AdoptCommentDto(dto.getAb_num(), dto.getContent(), dto.getId(), dto.getW_date(), dto.getNum()));
		return adoptComment;
	}
	
	/**
	 * 특정 댓글 조회
	 * @param dto
	 * @return
	 */
	public AdoptCommentDto getComment(int ab_num) {
		AdoptCommentDto dto = new AdoptCommentDto();
		dto = dao.getComment(ab_num);
		return dto;
	}
	
	/**
	 * 댓글 수정
	 * @param dto
	 * @return
	 */
	public AdoptCommentDto edit(AdoptCommentDto newComment) {
		AdoptCommentDto adoptCommentDto = dao.edit(new AdoptCommentDto(newComment.getAb_num(), newComment.getContent(), newComment.getId(), newComment.getW_date(), newComment.getNum()));
		return adoptCommentDto;
	}
	
	/**
	 * 댓글 삭제
	 * @param num
	 */
	public void remove(int num) {
		dao.remove(num);
	}
}