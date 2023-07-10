package com.example.demo.adopt;

import java.util.Date;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.example.demo.member.Member;

import jakarta.persistence.Column;
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
@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Adoptboard {
	
	@Id
	@SequenceGenerator(name="seq_adoptboard_num", sequenceName="seq_adoptboard_num", allocationSize=1)//시퀀스 생성. sequenceName:시퀀스 이름
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seq_adoptboard_num")//값 자동생성설정
	private int num;		// 개인 분양 게시판 고유 번호 (PK)
	
	@ManyToOne
	@JoinColumn(name="id", nullable=false)
	@OnDelete(action=OnDeleteAction.CASCADE)
	private Member id;	// 작성자 (Member 의 id FK)
	
	private String title;	// 제목
	private String content;	// 내용
	private String Category;
	private String gender;
	
	private Date w_date;	// 날짜
	private String address;	// 주소
	private String pic1;	// 첫번째 사진 파일 경로
	private String pic2;	// 두번째 사진 파일 경로
	
	@Column(nullable=true)
	private int likecnt; //좋아요 수
	private int cnt; // 조회수
	private int isCheck; //분양여부
	
	@PrePersist
	public void preprocess() {
		w_date = new Date();	// 현재 날짜 생성
		likecnt = 0;
	}
}