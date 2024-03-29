package com.example.demo.applyform;


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
public class Applyform {
	@Id
	@SequenceGenerator(name="seq_applyform", sequenceName="seq_applyform", allocationSize=1)//시퀀스 생성. sequenceName:시퀀스 이름
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seq_applyform")//값 자동생성설정
	private int num;
	
	@ManyToOne
	@JoinColumn(name="id", nullable=false)  //fk 설정
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Member id;
	private Date wdate;
	private String agreement;
	private String another;
	private String reason;
	private String feeding;
	private int ischeck;
	private String kindCd;
	private String sexCd;
	private String age;
	private String colorCd;
	private String neuterYn;
	private String careNm;
	private String careAddr;
	private String popfile;
	
	
	@PrePersist
	public void preprocess() {
		ischeck = 0;
		wdate = new Date(); //현재 날짜 생성
	}
}
