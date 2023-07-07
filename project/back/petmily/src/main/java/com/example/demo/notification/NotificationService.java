package com.example.demo.notification;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.member.Member;

@Service
public class NotificationService {

	@Autowired
	private NotificationDao dao;
	
	/**
	 * 새 알림 받는 사람으로 전체 검색
	 * @param id
	 * @return
	 */
	public ArrayList<NotificationDto> getNewNotification(String id) {
		Member memberId = new Member(id,"","","","",null,"","","",null);
		ArrayList<Notification> news = (ArrayList<Notification>) dao.newNotification(id);
		ArrayList<NotificationDto> dtos = new ArrayList<NotificationDto>();
		for (Notification e : news) {
			dtos.add(new NotificationDto(e.getNum(), e.getReciever(), e.getHeader(), e.getContent(), e.getRow_num(), e.getTitle(), e.getWriter(), e.getCommenter(), e.getNotify_type(), e.getIs_clicked(), e.getTr_date()));
		}
		return dtos;
	}
	
	/**
	 * 모든 알림 받는 사람으로 전체 검색
	 * @param id
	 * @return
	 */
	public ArrayList<NotificationDto> getAllNotification(String id) {
		Member memberId = new Member(id,"","","","",null,"","","",null);
		ArrayList<Notification> news = (ArrayList<Notification>) dao.allNotification(id);
		//ArrayList<Notification> news = (ArrayList<Notification>) dao.findByReciever(memberId);
		ArrayList<NotificationDto> dtos = new ArrayList<NotificationDto>();
		for (Notification e : news) {
			dtos.add(new NotificationDto(e.getNum(), e.getReciever(), e.getHeader(), e.getContent(), e.getRow_num(), e.getTitle(), e.getWriter(), e.getCommenter(), e.getNotify_type(), e.getIs_clicked(), e.getTr_date()));
		}
		return dtos;
	}
	
	/**
	 * 분양해요 게시판 댓글 알림
	 * @param id
	 * @return
	 */
	public ArrayList<NotificationDto> getAdoptCommentNotification(String id) {
		Member memberId = new Member(id,"","","","",null,"","","",null);
		ArrayList<Notification> news = (ArrayList<Notification>) dao.adoptNotification(id);
		//ArrayList<Notification> news = (ArrayList<Notification>) dao.adoptNotification(memberId);
		ArrayList<NotificationDto> dtos = new ArrayList<NotificationDto>();
		for (Notification e : news) {
			dtos.add(new NotificationDto(e.getNum(), e.getReciever(), e.getHeader(), e.getContent(), e.getRow_num(), e.getTitle(), e.getWriter(), e.getCommenter(), e.getNotify_type(), e.getIs_clicked(), e.getTr_date()));
		}
		return dtos;
	}
	
	/**
	 * 입양일지 게시판 댓글 알림
	 * @param id
	 * @return
	 */
	public ArrayList<NotificationDto> getDiaryCommentNotification(String id) {
		Member memberId = new Member(id,"","","","",null,"","","",null);
		ArrayList<Notification> news = (ArrayList<Notification>) dao.dbNotification(id);
		ArrayList<NotificationDto> dtos = new ArrayList<NotificationDto>();
		for (Notification e : news) {
			dtos.add(new NotificationDto(e.getNum(), e.getReciever(), e.getHeader(), e.getContent(), e.getRow_num(), e.getTitle(), e.getWriter(), e.getCommenter(), e.getNotify_type(), e.getIs_clicked(), e.getTr_date()));
		}
		return dtos;
	}
	
	/**
	 * 쪽지함 받은 쪽지 알림
	 * @param id
	 * @return
	 */
	public ArrayList<NotificationDto> getMessageNotification(String id) {
		Member memberId = new Member(id,"","","","",null,"","","",null);
		ArrayList<Notification> news = (ArrayList<Notification>) dao.msgboxNotification(id);
		ArrayList<NotificationDto> dtos = new ArrayList<NotificationDto>();
		for (Notification e : news) {
			dtos.add(new NotificationDto(e.getNum(), e.getReciever(), e.getHeader(), e.getContent(), e.getRow_num(), e.getTitle(), e.getWriter(), e.getCommenter(), e.getNotify_type(), e.getIs_clicked(), e.getTr_date()));
		}
		return dtos;
	}
	
	public void setNotifyTypeOpened(String id, ArrayList<Integer> numList) {
		for (int num : numList) {
			dao.deleteById(num);
		}
	}
	
	public void readNotification(int num) {
		dao.readNotification(num);
	}
	
	
}