package com.example.demo.watchlist;

import com.example.demo.diaryboard.Diaryboard;
import com.example.demo.member.Member;
import com.example.demo.volboard.Volboard;

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
public class WatchlistDto {
	private int db_num;
	private Member id;
	private Volboard num;
}
