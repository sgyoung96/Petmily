package com.example.demo.member;

import java.util.Date;

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
public class MemberDto {
	
	private String id;
	private String token;
	private String pwd;
	private String name;
	private String email;
	private Date birth;
	private String gender;
	private String phone;
	private String address;

}
