package com.example.demo.adoptcomment;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdoptCommentDao extends JpaRepository<Adoptcomment, Integer>{
		
	ArrayList<Adoptcomment> findByNum(int num); 				// 해당 게시글 전체 댓글 조회, num : 게시글 고유 번호	
}
