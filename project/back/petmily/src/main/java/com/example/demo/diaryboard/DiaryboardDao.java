package com.example.demo.diaryboard;

import java.util.ArrayList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.member.Member;

import jakarta.transaction.Transactional;
import java.util.List;

@Repository
public interface DiaryboardDao extends JpaRepository<Diaryboard, Integer> {
	ArrayList<Diaryboard> findByIdContaining(Member id);
	ArrayList<Diaryboard> findByTitleContaining(String title);
	ArrayList<Diaryboard> findAllByOrderByNumDesc();
	ArrayList<Diaryboard> findAllByOrderByLikecntDesc();
	ArrayList<Diaryboard> findById(Member id);
	
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
	
	// id를 받아와서 그 id의 작성한게시판 개수 출력
    @Transactional
    @Query(value = "SELECT COUNT(*) FROM Diaryboard WHERE id = :id")
    int countById(@Param("id") Member id);
	
	
	
}
