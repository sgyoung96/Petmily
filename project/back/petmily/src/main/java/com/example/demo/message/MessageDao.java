package com.example.demo.message;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.member.Member;

import jakarta.transaction.Transactional;
@Repository
public interface MessageDao extends JpaRepository<Message, Integer> {
	ArrayList<Message> findByRecieverAndAvailablereciever(Member loginId, String del); // 받은 메세지함 목록
	ArrayList<Message> findBySenderAndAvailablesender(Member sender, String del); // 보낸이로 검색	
	ArrayList<Message> findByRecieverAndCheckAndAvailablereciever(Member loginId, int check, String del); // 내가 받은 메세지 중 읽은 메세지, 안읽은 메세지 검색(삭제메세지 제외)
	ArrayList<Message> findBySenderAndCheckAndAvailablesender(Member loginId, int check, String del); // 내가 보낸 메세지 중 수신자가 읽은 메세지, 안읽은 메세지 검색(삭제메세지 제외)

	
	
	@Query("SELECT m FROM Message m WHERE m.reciever = :reciever AND m.title LIKE %:title% AND m.availablereciever = :availablereciever") //받은 메세지 제목으로 검색
	List<Message> findByTitleRecieve(@Param("reciever") Member reciever, @Param("title") String title, @Param("availablereciever") String availablereciever);
	
	@Query("SELECT m FROM Message m WHERE m.sender = :sender AND m.title LIKE %:title% AND m.availablesender = :availablesender") //보낸 메세지 제목으로 검색
	List<Message> findByTitleSend(@Param("sender") Member sender, @Param("title") String title, @Param("availablesender") String availablesender);
	
	@Transactional
	@Modifying
	@Query(value="update message set m_check=1 where num=:num", nativeQuery=true)
	void updatem_check(@Param("num") int num);
	
	@Transactional
	@Modifying
	@Query(value="update message set availablesender='N' where num=:num", nativeQuery=true)
	void updateavailablesender(@Param("num") int num);
	
	@Transactional
	@Modifying
	@Query(value="update message set availablereciever='N' where num=:num", nativeQuery=true)
	void updateavailablereciever(@Param("num") int num);
	
	@Transactional
    @Query(value = "SELECT COUNT(*) FROM Message WHERE reciever = :reciever")
    int countById(@Param("reciever") Member reciever);
}
