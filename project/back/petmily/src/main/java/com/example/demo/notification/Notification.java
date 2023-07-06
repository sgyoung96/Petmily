package com.example.demo.notification;

import java.util.Date;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.example.demo.member.Member;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.SequenceGenerator;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Notification {	// 트리거에 의해 쪽지 및 게시판 테이블에 insert 이벤트시 1행씩 삽입
	
	@Id
	@SequenceGenerator(name="seq_notify", sequenceName="seq_notify", allocationSize=1)//시퀀스 생성. sequenceName:시퀀스 이름
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seq_notify")//값 자동생성설정
	private int num;			// 시퀀스
	
	@ManyToOne
	@JoinColumn(name="reciever", nullable=true)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Member reciever;	// 알림 수신 받는 로그인한 사람
	
	private String header;		// [댓글알림] / [쪽지알림]
	private String content;		// 1, 2: 에 새로운 댓글이 달렸습니다. 3: ''
	private int row_num;		// 원글 시퀀스 번호
	private String title;		// 게시글 제목
	
	@ManyToOne
	@JoinColumn(name="writer", nullable=true)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Member writer;		// 게시글 작성자 및 쪽지 발송자
	@ManyToOne
	@JoinColumn(name="commenter", nullable=true)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Member commenter;	// 게시글 댓글 작성자 (쪽지 발송시 null 삽입)
	private String notify_type;	// 1,2 : 댓글알림 3: 쪽지알림 
	private String is_clicked;	// 삽입시 일괄 0 으로 등록, 알림뱃지를 1번이라도 누르면 1로 일괄 업데이트
	private Date tr_date;// 삽입 일자
	
	@PrePersist
	   public void preprocess() {
		tr_date = new Date(); //현재 날짜 생성
	   }
}
