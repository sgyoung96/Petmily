package com.example.demo.alarmtest.send;

import java.util.Date;

import org.springframework.web.multipart.MultipartFile;

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
public class AlarmSenderDto {
	private int num;		// 개인 고유 번호 (PK)
	private int clicked;
}
