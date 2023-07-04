package com.example.demo.adoptliketable;



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
public class AdoptliketableDto {
	private int db_num;
	private Member id;
	private Adoptboard num;
}
