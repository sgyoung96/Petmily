package com.example.demo.notification;

import java.util.ArrayList;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.member.Member;

@Repository
public interface NotificationDao extends JpaRepository<Notification, Integer> {

	/**
	 * 새 알림 받는 사람으로 전체 검색
	 * @param id
	 * @return
	 */
	@Transactional
	@Query(value="SELECT n FROM Notification n JOIN n.reciever r WHERE r = :reciever AND n.isClicked = '0' ORDER BY n.trDate DESC")
	ArrayList<Notification> getNew2Notification(@Param("reciever") Member reciever);

	
	/**
	 * 모든 알림 받는 사람으로 전체 검색
	 * @param id
	 * @return
	 */
	@Transactional
	@Query(value = "SELECT * FROM Notification WHERE RECIEVER = :reciever ORDER BY TR_DATE DESC")
	ArrayList<Notification> getAll2Notification(@Param("reciever") Member reciever);

	
	/**
	 * 분양해요 게시판 댓글 알림
	 * @param id
	 * @return
	 */
	@Transactional
	@Query(value="SELECT n FROM Notification n JOIN n.receiver r JOIN n.board b WHERE r = :receiver AND b INSTANCE OF AdoptBoard AND n.notifyType = '1' ORDER BY n.trDate DESC")
	ArrayList<Notification> getAdoptComment2Notification(@Param("receiver") Member receiver);

	
	/**
	 * 입양일지 게시판 댓글 알림
	 * @param id
	 * @return
	 */
	@Transactional
	@Query(value="SELECT A.*"
			   + "  FROM Notification A, DIARYBOARD B"
			   + " WHERE A.RECIEVER = :reciever"
			   + "   AND B.ID = A.RECIEVER"
			   + "   AND A.NOTIFY_TYPE = '2'"
			   + " ORDER BY A.TR_DATE DESC")
	ArrayList<Notification> getDiaryComment2Notification(@Param("reciever") Member reciever);
	
	/**
	 * 쪽지함 받은 쪽지 알림
	 * @param id
	 * @return
	 */
	@Transactional
	@Query(value="SELECT *"
			   + "  FROM Notification"
			   + " WHERE RECIEVER = :reciever"
			   + "   AND NOTIFY_TYPE = '3'"
			   + " ORDER BY TR_DATE DESC")
	ArrayList<Notification> getMessage2Notification(@Param("reciever") Member reciever);
}