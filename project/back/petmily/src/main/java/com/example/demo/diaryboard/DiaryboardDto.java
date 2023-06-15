package com.example.demo.diaryboard;

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
public class DiaryboardDto {
	private int num;
	private String title;
	private String content;
	private Date w_date;
	private Member id;
	private String pic1;
	private String pic2;
	private MultipartFile[] f = new MultipartFile[2];
}
