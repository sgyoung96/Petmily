package com.example.demo.liketable;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.example.demo.diaryboard.Diaryboard;
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
import lombok.ToString;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Liketable {
	@Id
	@SequenceGenerator(name="seq_like2", sequenceName="seq_like2", allocationSize=1)//시퀀스 생성. sequenceName:시퀀스 이름
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seq_like2")//값 자동생성설정
	private int db_num;
	
	@ManyToOne
	@JoinColumn(name="id", nullable=false)  //fk 설정
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Member id;
	
	@ManyToOne
	@JoinColumn(name="num", nullable=false)  //fk 설정
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Diaryboard num;
}
