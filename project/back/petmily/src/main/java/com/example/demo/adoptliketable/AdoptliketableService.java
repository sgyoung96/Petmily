package com.example.demo.adoptliketable;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.example.demo.adopt.Adoptboard;
import com.example.demo.member.Member;

@Service
public class AdoptliketableService {
	@Autowired
	private AdoptliketableDao dao;
	
	AdoptliketableDto getDuplicate(String id, int num) {
		Member m = new Member(id,"","","","",null,"","","",null);
		Adoptboard d = new Adoptboard(num,null,null,null,null,null,null,"","","",0);
		Adoptliketable dto = dao.findByIdAndNum(m, d);
		if(dto == null) {
			return null;
		}else {
			return new AdoptliketableDto(dto.getDb_num(), dto.getId(), dto.getNum());
		}
	}
	
	void save(AdoptliketableDto dto) {
		dao.save(new Adoptliketable(dto.getDb_num(), dto.getId(), dto.getNum()));
	}
	
	void deleteById(String id, int num) {
		Member m = new Member(id,"","","","",null,"","","",null);
		Adoptboard d = new Adoptboard(num,null,null,null,null,null,null,"","","",0);
		dao.deleteByIdAndNum(m, d);
	}
	
	void deleteOne(AdoptliketableDto dto) {
		dao.delete(new Adoptliketable(dto.getDb_num(), dto.getId(), dto.getNum()));
	}
}
