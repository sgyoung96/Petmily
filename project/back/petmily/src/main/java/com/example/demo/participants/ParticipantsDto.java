package com.example.demo.participants;


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
public class ParticipantsDto {
	
	private int num;
	private Volboard board_num;
	private Member id;
}
