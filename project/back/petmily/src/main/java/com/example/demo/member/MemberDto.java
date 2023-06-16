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

	@Override
	public boolean equals(Object obj) { // equals는 object를 상속받았는데 object는 참조값이 같으면 같다
		// TODO Auto-generated method stub
		if(obj!=null && obj instanceof MemberDto) { // 재정의했다. 꼭 필요한건 아니지만 테스팅하려고 재정의함. 
			MemberDto d = (MemberDto)obj;
			if(d.id.equals(this.id)) {
				return true;
			}
		}
		return false;
	}
	

	
	
}
