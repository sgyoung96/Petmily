package com.example.demo.member;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Member {
	
	@Id
	private String id;
	private String token;
	private String pwd;
	private String name;
	private String email;
	private Date birth;
	private String gender;
	private String phone;
	private String address;
	
	@Column(nullable=true)
	private String profile;
	

}
