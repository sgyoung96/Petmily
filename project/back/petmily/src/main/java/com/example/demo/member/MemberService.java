package com.example.demo.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberService {
	@Autowired
	private MemberDao dao;
	
	//회원가입, 내정보수정
	public MemberDto save(MemberDto dto) {
		System.out.println("회원가입");
		System.out.println(dto.getBirth());
		System.out.println(dto);
		Member entity = dao.save(new Member(dto.getId(), dto.getToken(),dto.getPwd(),dto.getName(),dto.getEmail(),dto.getBirth(),dto.getGender(),dto.getPhone(),dto.getAddress()));
	return new MemberDto(entity.getId(), entity.getToken(), entity.getPwd(),entity.getName(),entity.getEmail(),entity.getBirth(),entity.getGender(),entity.getPhone(),entity.getAddress());
	}
	
	//로그인, 내정보확인
	public MemberDto getMember(String id) {
		Member m = dao.findById(id).orElse(null);
		if(m==null) {
			return null;
		}
		return new MemberDto(m.getId(), m.getToken(),m.getPwd(),m.getName(),m.getEmail(),m.getBirth(),m.getGender(),m.getPhone(),m.getAddress());
		}
		
		
	//탈퇴
	public void delMember(String id) {
		dao.deleteById(id);
	}
		
}
