package com.example.demo.participants;

import org.hibernate.annotations.OnDelete;

import org.hibernate.annotations.OnDeleteAction;

import com.example.demo.member.Member;
import com.example.demo.volboard.Volboard;

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
@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Participants {
	@Id
	@SequenceGenerator(name="seq_part", sequenceName="seq_part", allocationSize=1)//시퀀스 생성. sequenceName:시퀀스 이름
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seq_part")//값 자동생성설정
	private int num;
	
	@ManyToOne
	@JoinColumn(name="board_num", nullable=false)  //fk 설정
	@OnDelete(action = OnDeleteAction.CASCADE)
	Volboard board_num;
	
	@ManyToOne
	@JoinColumn(name="id", nullable=false)  //fk 설정
	@OnDelete(action = OnDeleteAction.CASCADE)
	Member id;
}
