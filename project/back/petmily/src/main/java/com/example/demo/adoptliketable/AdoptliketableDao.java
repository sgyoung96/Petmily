package com.example.demo.adoptliketable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.adopt.Adoptboard;
import com.example.demo.member.Member;

@Repository
public interface AdoptliketableDao extends JpaRepository<Adoptliketable, Integer> {
	Adoptliketable findByIdAndNum(Member id, Adoptboard num);
	void deleteByIdAndNum(Member id, Adoptboard num);
	
	// id를 받아와서 그 id의 좋아요 개수 출력
    @Transactional
    @Query(value = "SELECT COUNT(*) FROM Adoptliketable WHERE id = :id")
    int countById(@Param("id") Member id);
}
