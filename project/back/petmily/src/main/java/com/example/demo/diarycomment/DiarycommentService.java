package com.example.demo.diarycomment;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.diaryboard.Diaryboard;

@Service
public class DiarycommentService {
	@Autowired
	private DiarycommentDao dao;
	
	//추가, 수정
	public int save(DiarycommentDto dto) {
		Diarycomment c = dao.save(new Diarycomment(dto.getDb_num(), dto.getContent(), dto.getW_date(), dto.getId(), dto.getNum()));
		return c.getDb_num();
	}
	
	//댓글리스트
	public ArrayList<DiarycommentDto> getByNum(int num){
		Diaryboard db = new Diaryboard(num, "", "", null, null, "", "");
		ArrayList<Diarycomment> list = (ArrayList<Diarycomment>) dao.findByNum(db);
		ArrayList<DiarycommentDto> list2 = new ArrayList<DiarycommentDto>();
		for(Diarycomment c:list) {
			list2.add(new DiarycommentDto(c.getDb_num(), c.getContent(), c.getW_date(), c.getId(), c.getNum()));
		}
		return list2;
	}
	
	public void delDiarycomment(int num) {
		dao.deleteById(num);
	}
}
