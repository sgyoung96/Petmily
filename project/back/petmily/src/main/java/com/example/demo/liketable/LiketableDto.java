package com.example.demo.liketable;



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
public class LiketableDto {
	private int db_num;
	private Member id;
	private Diaryboard num;
}
