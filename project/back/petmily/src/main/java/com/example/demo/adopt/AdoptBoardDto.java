package com.example.demo.adopt;

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
public class AdoptBoardDto {
	private int num;		// 개인 분양 게시판 고유 번호 (PK)
	private Member id;		// 작성자 (Member 의 id FK)
	private String title;	// 제목
	private String content;	// 내용
	private Date w_date;	// 날짜
	private String address;	// 주소
	private String pic1;	// 첫번째 사진 파일 경로
	private String pic2;	// 두번째 사진 파일 경로
	private MultipartFile[] multipartFile = new MultipartFile[2];
}
