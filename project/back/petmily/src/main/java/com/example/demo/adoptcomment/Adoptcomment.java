package com.example.demo.adoptcomment;

import java.util.Date;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.example.demo.adapt.Adoptboard;
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
public class Adoptcomment {
	
	@Id
	@SequenceGenerator(name="seq_adopt_num", sequenceName="seq_adopt_num", allocationSize=1)//시퀀스 생성. sequenceName:시퀀스 이름
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seq_adopt_num")//값 자동생성설정
	private int ab_num;		// 고유번호
	private String content;	// 내용
	
	@ManyToOne
	@JoinColumn(name="id", nullable=false)  //fk 설정
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Member id;		// (FK)
	
	private Date w_date;		// 작성일
	
	@ManyToOne
	@JoinColumn(name="num", nullable=false)  //fk 설정
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Adoptboard num;		// 분양 게시글 고유 번호
	
	@PrePersist
	public void preprocess() {
		w_date = new Date(); //현재 날짜 생성
	}
}