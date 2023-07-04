package com.example.demo.diaryboard;

import java.util.ArrayList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.member.Member;

import jakarta.transaction.Transactional;
@Repository
public interface DiaryboardDao extends JpaRepository<Diaryboard, Integer> {
	ArrayList<Diaryboard> findByIdContaining(Member id);
	ArrayList<Diaryboard> findByTitleContaining(String title);
	ArrayList<Diaryboard> findAllByOrderByNumDesc();
	ArrayList<Diaryboard> findAllByOrderByLikecnt();
	
	@Transactional
	@Modifying
	@Query(value="update diaryboard set likecnt=likecnt+1 where num=:num", nativeQuery = true)
	void upCount(@Param("num") int num);
	
	@Transactional
	@Modifying
	@Query(value="update diaryboard set likecnt=likecnt-1 where num=:num", nativeQuery = true)
	void downCount(@Param("num") int num);
	
	@Transactional
	@Query(value= "SELECT COUNT(*) FROM Diaryboard")
	int countByAll();
	
	
	
}
