package com.example.demo.notification;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.member.Member;

import jakarta.transaction.Transactional;

@Repository
public interface NotificationDao extends JpaRepository<Notification, Integer> {

	/**
	 * 새 알림 받는 사람으로 전체 검색
	 * @param id
	 * @return
	 */

	@Query(value = "SELECT num, reciever, header, content, row_num, title, writer, commenter, notify_type, is_clicked, tr_date FROM Notification WHERE reciever = :reciever AND is_clicked = '0' ORDER BY tr_date DESC", nativeQuery = true)
	ArrayList<Notification> newNotification(@Param("reciever") String reciever);
	
	/**
	 * 모든 알림 받는 사람으로 전체 검색
	 * @param id
	 * @return
	 */
	@Transactional
	@Query(value = "SELECT num, reciever, header, content, row_num, title, writer, commenter, notify_type, is_clicked, tr_date FROM Notification WHERE reciever = :reciever ORDER BY tr_date DESC", nativeQuery = true)
	ArrayList<Notification> allNotification(@Param("reciever") String reciever);

	
	//ArrayList<Notification> findByReciever(Member reciever);
	
	/**
	 * 분양해요 게시판 댓글 알림
	 * @param id
	 * @return
	 */

	@Query(value = "SELECT num, reciever, header, content, row_num, title, writer, commenter, notify_type, is_clicked, tr_date FROM Notification WHERE reciever = :reciever AND notify_type = '1' ORDER BY tr_date DESC", nativeQuery = true)
	ArrayList<Notification> adoptNotification(@Param("reciever") String reciever);
	
	/**
	 * 입양일지 게시판 댓글 알림
	 * @param id
	 * @return
	 */
	@Query(value = "SELECT num, reciever, header, content, row_num, title, writer, commenter, notify_type, is_clicked, tr_date FROM Notification WHERE reciever = :reciever AND notify_type = '2' ORDER BY tr_date DESC", nativeQuery = true)
	ArrayList<Notification> dbNotification(@Param("reciever") String reciever);
	
	/**
	 * 쪽지함 받은 쪽지 알림
	 * @param id
	 * @return
	 */
	@Query(value = "SELECT num, reciever, header, content, row_num, title, writer, commenter, notify_type, is_clicked, tr_date FROM Notification WHERE reciever = :reciever AND notify_type = '3' ORDER BY tr_date DESC", nativeQuery = true)
	ArrayList<Notification> msgboxNotification(@Param("reciever") String reciever);
}