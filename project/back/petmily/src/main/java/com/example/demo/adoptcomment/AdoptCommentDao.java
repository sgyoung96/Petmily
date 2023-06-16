package com.example.demo.adoptcomment;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdoptCommentDao extends JpaRepository<Adoptcomment, Integer>{
		
	ArrayList<Adoptcomment> getAll(int num); 					// 해당 게시글 전체 댓글 조회, num : 게시글 고유 번호
	Adoptcomment save(AdoptCommentDto adoptComment);			// 댓글 입력 및 수정
	AdoptCommentDto findByNum(int num);							// 특정 댓글 정보 조회
	void deleteByNum(int num);									// 댓글 삭제
	
}
