package com.example.demo.adoptcomment;

import java.util.Date;

import com.example.demo.adopt.Adoptboard;
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
public class AdoptCommentDto {
	private int ab_num;						// 고유번호
	private String content;					// 내용
	private Member id;						// (FK)
	private Date w_date;					// 작성일
	private Adoptboard num;					// 분양 게시글 고유 번호
}
