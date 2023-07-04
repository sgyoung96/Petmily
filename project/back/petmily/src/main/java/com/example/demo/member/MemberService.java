package com.example.demo.member;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberService {
	@Autowired
	private MemberDao dao;
	
	//회원가입, 내정보수정
	public MemberDto save(MemberDto dto) {
		System.out.println("회원가입");
		System.out.println("dto.birth : " +  dto.getBirth());
		System.out.println("dto : " + dto);
		Member entity = dao.save(new Member(dto.getId(), dto.getToken(),dto.getPwd(),dto.getName(),dto.getEmail(),dto.getBirth(),dto.getGender(),dto.getPhone(),dto.getAddress(),dto.getProfile()));
	return new MemberDto(entity.getId(), entity.getToken(), entity.getPwd(),entity.getName(),entity.getEmail(),entity.getBirth(),entity.getGender(),entity.getPhone(),entity.getAddress(),entity.getProfile(),null);
	}
	
	//로그인, 내정보확인
	public MemberDto getMember(String id) {
		Member m = dao.findById(id).orElse(null);
		if(m==null) {
			return null;
		}
		return new MemberDto(m.getId(), m.getToken(),m.getPwd(),m.getName(),m.getEmail(),m.getBirth(),m.getGender(),m.getPhone(),m.getAddress(),m.getProfile(),null);
		}
		
	//이메일 중목 확인
	public ArrayList<MemberDto> getByEmail(String email) {
		ArrayList<Member> list = (ArrayList<Member>)dao.findByEmail(email);
		ArrayList<MemberDto> list2 = new ArrayList<MemberDto>();
		if(list.size()==0) {
			return null;
		}else {
			for(Member m:list) {
				list2.add(new MemberDto(m.getId(), m.getToken(),m.getPwd(),m.getName(),m.getEmail(),m.getBirth(),m.getGender(),m.getPhone(),m.getAddress(),m.getProfile(),null));
			}
		}
		
		return list2;
		
	}
	
	//멤버 전체 목록
	public ArrayList<MemberDto> getAll(){
		System.out.println("멤버 전체 목록 service");
		ArrayList<Member> list=(ArrayList<Member>)dao.findAll();
		ArrayList<MemberDto> list2 = new ArrayList<MemberDto>();
		for(Member m:list) {
			list2.add(new MemberDto(m.getId(), m.getToken(),m.getPwd(),m.getName(),m.getEmail(),m.getBirth(),m.getGender(),m.getPhone(),m.getAddress(),m.getProfile(),null));
		}
		return list2;
	}
	
		
	//탈퇴
	public void delMember(String id) {
		dao.deleteById(id);
	}
		
}
