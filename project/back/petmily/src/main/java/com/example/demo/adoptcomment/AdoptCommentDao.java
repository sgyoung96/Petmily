package com.example.demo.adoptcomment;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AdoptCommentDao extends JpaRepository<Adoptcomment, Integer>{
		
	ArrayList<Adoptcomment> getAll(int num); 				// 해당 게시글 전체 댓글 조회, num : 게시글 고유 번호
	Adoptcomment add(Adoptcomment adoptComment); 			// 댓글 입력
	AdoptCommentDto getComment(int num);					// 특정 댓글 정보 조회
	AdoptCommentDto edit(AdoptCommentDto adoptCommentDto); 	// 댓글 수정
	void remove(int num);									// 댓글 삭제
	
}
