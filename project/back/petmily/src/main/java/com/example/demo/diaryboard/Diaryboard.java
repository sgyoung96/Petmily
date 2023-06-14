package com.example.demo.diaryboard;

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
public class Diaryboard {
	@Id
	@SequenceGenerator(name="seq_gen", sequenceName="seq_diaryboard", allocationSize=1)//시퀀스 생성. sequenceName:시퀀스 이름
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seq_diaryboard")//값 자동생성설정
	private int num;
	private String title;
	private String content;
	private Date date;
	@ManyToOne
	@JoinColumn(name="writer", nullable=false)  //fk 설정
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Member writer;
	@Column(nullable=true)
	private String pic1;
	@Column(nullable=true)
	private String pic2;
	
	@PrePersist
	   public void preprocess() {
	      date = new Date(); //현재 날짜 생성
	   }
}
