package com.example.demo.adoptcomment;

import java.util.Date;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.data.annotation.Id;

import com.example.demo.member.Member;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
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
public class Adoptcomment {
	
	@Id
	@SequenceGenerator(name="seq_adaopt_num", sequenceName="seq_adaopt_num", allocationSize=1)//시퀀스 생성. sequenceName:시퀀스 이름
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seq_adaopt_num")//값 자동생성설정
	private int num;		// 고유번호
	private String comment;	// 내용
	
	@ManyToOne
	@JoinColumn(name="member", nullable=false)  //fk 설정
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Member id;		// (FK)
	
	private Date date;		// 작성일
	private int ab_num;		// 분양 게시글 고유 번호
	
	@PrePersist
	public void preprocess() {
		date = new Date(); //현재 날짜 생성
	}
}