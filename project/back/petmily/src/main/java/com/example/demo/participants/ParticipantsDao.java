package com.example.demo.participants;

import java.util.ArrayList;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.member.Member;
import com.example.demo.volboard.Volboard;


@Repository
public interface ParticipantsDao extends JpaRepository<Participants, Integer> {

    // 봉사게시판 번호를 받아와서 현재모집인원 반환
    @Transactional
    @Query(value = "SELECT COUNT(p) FROM Participants p WHERE p.boardnum.num = :boardNum")
    int countByBoardNum(@Param("boardNum") int boardNum);
    
    ArrayList<Participants> findByIdAndBoardnum(Member id, Volboard boardnum);
    
    ArrayList<Participants> findById(Member id);
    
    ArrayList<Participants> findByBoardnum(Volboard boardnum);
    
    // 봉사게시판 번호와 맴버 id를 받아와서 한 줄 삭제
    @Transactional
    void deleteByIdAndBoardnum(Member id, Volboard boardnum);

}