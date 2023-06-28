package com.example.demo.liketable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.diaryboard.Diaryboard;
import com.example.demo.member.Member;

@Service
public class LiketableService {
	@Autowired
	private LiketableDao dao;
	
	LiketableDto getDuplicate(String id, int num) {
		Member m = new Member(id,"","","","",null,"","","",null);
		Diaryboard d = new Diaryboard(num,"","",null,null,"","",0);
		Liketable dto = dao.findByIdAndNum(m, d);
		if(dto == null) {
			return null;
		}else {
			return new LiketableDto(dto.getDb_num(), dto.getId(), dto.getNum());
		}
	}
}
