package com.example.demo.volboard;

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
public class Volboard {
	@Id
	@SequenceGenerator(name="seq_vol", sequenceName="seq_vol", allocationSize=1)//시퀀스 생성. sequenceName:시퀀스 이름
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seq_vol")//값 자동생성설정
	private int num;
	
	@ManyToOne
	@JoinColumn(name="writer", nullable=false)  //fk 설정
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Member writer;
	
	private String title;
	private String content;
	private Date vol_date;
	private int vol_number;
	private Date w_date;
	private String address;
	
	@Column(nullable=true)
	private String pic1;
	
	@Column(nullable=true)
	private String pic2;
	
	private int count;
}
