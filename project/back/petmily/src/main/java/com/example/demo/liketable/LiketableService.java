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
	
	void save(LiketableDto dto) {
		dao.save(new Liketable(dto.getDb_num(), dto.getId(), dto.getNum()));
	}
	
	void deleteById(String id, int num) {
		Member m = new Member(id,"","","","",null,"","","",null);
		Diaryboard d = new Diaryboard(num,"","",null,null,"","",0);
		dao.deleteByIdAndNum(m, d);
	}
	
	void deleteOne(LiketableDto dto) {
		dao.delete(new Liketable(dto.getDb_num(), dto.getId(), dto.getNum()));
	}
	
	//관심수 출력
	public int printPerson(String id) {
		Member m = new Member(id,"","","","",null,"","","",null);
		return dao.countById(m);
	}
}
