package com.example.demo.diarycomment;

import java.util.ArrayList;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.diaryboard.Diaryboard;
import com.example.demo.member.Member;
@Repository
public interface DiarycommentDao extends JpaRepository<Diarycomment, Integer> {
	ArrayList<Diarycomment> findByNum(Diaryboard num);
	
	// id를 받아와서 그 id의 작성한게시판 개수 출력
    @Transactional
    @Query(value = "SELECT COUNT(*) FROM Diarycomment WHERE id = :id")
    int countById(@Param("id") Member id);
}
