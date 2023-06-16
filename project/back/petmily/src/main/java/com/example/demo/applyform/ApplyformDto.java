package com.example.demo.applyform;

import java.util.Date;

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
public class ApplyformDto {
	private int num;
	private Member id;
	private Date wdate;
	private String agreement;
	private String another;
	private String reason;
	private String feeding;
	private int ischeck;
}
