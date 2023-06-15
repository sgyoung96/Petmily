package com.example.demo.adapt;

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
@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Adoptboard {
	
	@Id
	@SequenceGenerator(name="seq_adaoptboard_num", sequenceName="seq_adaoptboard_num", allocationSize=1)//시퀀스 생성. sequenceName:시퀀스 이름
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seq_adaoptboard_num")//값 자동생성설정
	private int num;		// 개인 분양 게시판 고유 번호 (PK)
	
	@ManyToOne
	@JoinColumn(name="writer", nullable=false)
	@OnDelete(action=OnDeleteAction.CASCADE)
	private Member writer;	// 작성자 (Member 의 id FK)
	
	private String title;	// 제목
	private String content;	// 내용
	private Date w_date;	// 날짜
	private String address;	// 주소
	private String pic1;	// 첫번째 사진 파일 경로
	private String pic2;	// 두번째 사진 파일 경로
	
	@PrePersist
	public void preprocess() {
		w_date = new Date();	// 현재 날짜 생성
	}
}