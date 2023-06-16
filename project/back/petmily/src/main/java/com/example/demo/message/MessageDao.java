package com.example.demo.message;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.member.Member;

import jakarta.transaction.Transactional;
@Repository
public interface MessageDao extends JpaRepository<Message, Integer> {
	ArrayList<Message> findByReciever(Member loginId); // 받은 메세지함 목록
	ArrayList<Message> findBySender(Member sender); // 보낸이로 검색
	ArrayList<Message> findByRecieverAndCheck(Member loginId, int check); // 읽은 메세지, 안읽은 메세지 검색
	
	@Transactional
	@Modifying
	@Query(value="update message set m_check=1 where num=:num", nativeQuery=true)
	void updatem_check(@Param("num") int num);
}
