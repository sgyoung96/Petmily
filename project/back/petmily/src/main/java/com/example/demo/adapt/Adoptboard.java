package com.example.demo.adapt;

import java.util.Date;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.data.annotation.Id;

import com.example.demo.member.Member;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
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
	private int num;		// 개인 분양 게시판 고유 번호 (PK)
	
	@ManyToOne
	@JoinColumn(name="writer", nullable=false)
	@OnDelete(action=OnDeleteAction.CASCADE)
	private Member writer;	// 작성자 (Member 의 id FK)
	
	private String title;	// 제목
	private String content;	// 내용
	private Date date;	// 날짜
	private String address;	// 주소
	private String pic1;	// 첫번째 사진 파일 경로
	private String pic2;	// 두번째 사진 파일 경로
	
	@PrePersist
	public void preprocess() {
		date = new Date();	// 현재 날짜 생성
	}
}