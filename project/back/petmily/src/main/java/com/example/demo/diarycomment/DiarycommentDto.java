package com.example.demo.diarycomment;

import java.util.Date;

import org.springframework.web.multipart.MultipartFile;

import com.example.demo.diaryboard.Diaryboard;
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
public class DiarycommentDto {
	private int db_num;
	private String content;
	private Date w_date;
	private Member id;
	private Diaryboard num;
	
}
