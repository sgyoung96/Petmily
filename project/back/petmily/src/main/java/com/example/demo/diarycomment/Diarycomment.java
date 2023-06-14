package com.example.demo.diarycomment;

import java.util.Date;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

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
public class Diarycomment {
	@Id
	@SequenceGenerator(name="seq_gen", sequenceName="seq_diaryboard", allocationSize=1)//시퀀스 생성. sequenceName:시퀀스 이름
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seq_diaryboard")//값 자동생성설정
	private int num;
	private String commnet;
	private String id;
	private Date date;
	private int db_num;
	@ManyToOne
	@JoinColumn(name="id", nullable=false)  //fk 설정
	@OnDelete(action = OnDeleteAction.CASCADE)
	
	@PrePersist
	   public void preprocess() {
	      date = new Date(); //현재 날짜 생성
	   }
}