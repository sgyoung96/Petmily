package com.example.demo.liketable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.diaryboard.Diaryboard;
import com.example.demo.member.Member;

@Repository
public interface LiketableDao extends JpaRepository<Liketable, Integer> {
	Liketable findByIdAndNum(Member id, Diaryboard num);
	void deleteByIdAndNum(Member id, Diaryboard num);
	
	// id를 받아와서 그 id의 좋아요 개수 출력
    @Transactional
    @Query(value = "SELECT COUNT(*) FROM Liketable WHERE id = :id")
    int countById(@Param("id") Member id);
}
