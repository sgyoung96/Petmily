package com.example.demo.notification;

import org.springframework.stereotype.Repository;

@Repository
public class NotificationDto {
	private int id;				// 시퀀스
	private String receiver;	// 알림 수신 받는 로그인한 사람
	private String header;		// [댓글알림] / [쪽지알림] / [봉사모집]
	private String content;		// 0, 1: 에 새로운 댓글이 달렸습니다. 2: '', 3: 에 새 참여자가 모집되었습니다.
	private String board_title;	// 게시글 제목
	private String notify_type;	// 0, 1 : 댓글알림, 2: 쪽지알림 3: 봉사모집
	private String is_clicked;	// 삽입시 일괄 0 으로 등록, 알림뱃지를 1번이라도 누르면 1로 일괄 업데이트
}
