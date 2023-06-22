package com.example.demo.message;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Value;

import com.example.demo.member.Member;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class MessageDto {

private int num;
	

	private Member sender;
	private Member reciever;
	private LocalDate send_dt;
	private String title;
	private String content;
	private int check;
	private String availablesender;
	private String availablereciever;
}
