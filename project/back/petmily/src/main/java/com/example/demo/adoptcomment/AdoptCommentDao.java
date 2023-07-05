package com.example.demo.adoptcomment;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.adopt.Adoptboard;
import com.example.demo.member.Member;

@Repository
public interface AdoptCommentDao extends JpaRepository<Adoptcomment, Integer>{
		
	ArrayList<Adoptcomment> findByNum(Adoptboard num); 				// 해당 게시글 전체 댓글 조회, num : 게시글 고유 번호
	
	// id를 받아와서 그 id의 작성한댓글 개수 출력
    @Transactional
    @Query(value = "SELECT COUNT(*) FROM Adoptcomment WHERE id = :id")
    int countById(@Param("id") Member id);
}
