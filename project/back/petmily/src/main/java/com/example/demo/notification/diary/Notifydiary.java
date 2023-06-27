package com.example.demo.notification.diary;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.example.demo.diaryboard.Diaryboard;
import com.example.demo.diarycomment.Diarycomment;
import com.example.demo.member.Member;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Notifydiary {

	@Id
	@SequenceGenerator(name="seq_notify_diary", sequenceName="seq_notify_diary", allocationSize=1)//시퀀스 생성. sequenceName:시퀀스 이름
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seq_notify_diary")//값 자동생성설정
	private int num;

	@ManyToOne
	@JoinColumn(name="id", nullable=false)  //fk 설정
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Member id;
	
	@ManyToOne
	@JoinColumn(name="name", nullable=false)  //fk 설정
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Member name;
	
	@ManyToOne
	@JoinColumn(name="title", nullable=false)  //fk 설정
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Diaryboard title;
	
	private String content;

	@ManyToOne
	@JoinColumn(name="w_date", nullable=false)  //fk 설정
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Diarycomment instTime;
	
	private String isClicked;
}
