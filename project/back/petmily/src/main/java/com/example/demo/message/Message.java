package com.example.demo.message;

import java.time.LocalDate;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.format.annotation.DateTimeFormat;

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
public class Message {
	
	@Id
	@SequenceGenerator(name="seq_message",sequenceName="seq_message", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="seq_message")
	private int num;
	
	@ManyToOne
	@JoinColumn(name="sender", nullable=false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Member sender;
	
	@ManyToOne
	@JoinColumn(name="reciever", nullable=false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Member reciever;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate send_dt;
	private String title;
	private String content;
	@Column(name="m_check")
	private int check; // 메세지 확인 여부(default 0 : 안읽음, 1 : 읽음)
	private String availablesender;
	private String availablereciever;
	
	@PrePersist
	public void preprocess() {
		send_dt = LocalDate.now(); 
		availablesender = "A";
		availablereciever = "A";
	}
	
	

}
