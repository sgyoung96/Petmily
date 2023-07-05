package com.example.demo.adopt;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.member.Member;

@Repository
public interface AdoptBoardDao extends JpaRepository<Adoptboard, Integer> {
    ArrayList<Adoptboard> findById(Member id);
    ArrayList<Adoptboard> findByTitle(String title);
    ArrayList<Adoptboard> findAllByOrderByLikecnt();

    @Transactional
    @Query(value = "SELECT COUNT(*) FROM Adoptboard")
    int countByAll();
    
    @Transactional
	@Modifying
	@Query(value="update adoptboard set likecnt=likecnt+1 where num=:num", nativeQuery = true)
	void upCount(@Param("num") int num);
	
	@Transactional
	@Modifying
	@Query(value="update adoptboard set likecnt=likecnt-1 where num=:num", nativeQuery = true)
	void downCount(@Param("num") int num);
	
	// id를 받아와서 그 id의 작성한게시판 개수 출력
    @Transactional
    @Query(value = "SELECT COUNT(*) FROM Adoptboard WHERE id = :id")
    int countById(@Param("id") Member id);
}