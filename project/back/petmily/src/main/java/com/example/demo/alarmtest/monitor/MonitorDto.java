package com.example.demo.alarmtest.monitor;

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
public class MonitorDto {
	private int num;		
	private String content;
}
