package com.example.demo.participants;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import jakarta.transaction.Transactional;

public interface ParticipantsDao extends JpaRepository<Participants, Integer> {
	
		//봉사게시판 번호를 받아와서 현재모집인원 반환
		@Transactional
		@Query(value="SELECT COUNT(p) FROM Participants p WHERE p.boardNum.num = :boardNum", nativeQuery = true)
		long countByBoardNum(@Param("boardNum") int boardNum);

}
