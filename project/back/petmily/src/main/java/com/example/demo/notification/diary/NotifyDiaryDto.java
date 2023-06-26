package com.example.demo.notification.diary;

import java.util.Date;

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
public class NotifyDiaryDto {
	private int num;
	private Member id;
	private Diaryboard dbnum;
	private String content;
	private Date instTime;
	private String isClicked;
}
