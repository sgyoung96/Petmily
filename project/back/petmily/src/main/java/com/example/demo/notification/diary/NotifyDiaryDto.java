package com.example.demo.notification.diary;

import com.example.demo.diaryboard.Diaryboard;
import com.example.demo.diarycomment.Diarycomment;
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
public class NotifyDiaryDto {
	private int num;
	private Member id;
	private Member name;
	private Diaryboard title;
	private String content;
	private Diarycomment instTime;
	private String isClicked;
}
