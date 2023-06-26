package com.example.demo.diaryboard;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.member.Member;

@Service
public class DiaryboardService {
	@Autowired
	private DiaryboardDao dao;

	// 추가, 수정
	public int save(DiaryboardDto dto) {
		Diaryboard d = dao.save(new Diaryboard(dto.getNum(), dto.getTitle(), dto.getContent(), dto.getW_date(),
				dto.getId(), dto.getPic1(), dto.getPic2()));
		return d.getNum();
	}

	// 상품 전체 검색
	public ArrayList<DiaryboardDto> getAll() {
		ArrayList<Diaryboard> list = (ArrayList<Diaryboard>) dao.findAllByOrderByNumDesc();
		ArrayList<DiaryboardDto> list2 = new ArrayList<DiaryboardDto>();
		for (Diaryboard d : list) {
			list2.add(new DiaryboardDto(d.getNum(), d.getTitle(), d.getContent(), d.getW_date(), d.getId(), d.getPic1(),
					d.getPic2(), null));
		}
		return list2;
	}
	
	//번호로 검색
	public DiaryboardDto getByNum(int num) {
		Diaryboard d = dao.findById(num).orElse(null);
		if(d==null) {
			return null;
		}
		return new DiaryboardDto(d.getNum(), d.getTitle(), d.getContent(), d.getW_date(), d.getId(), d.getPic1(),
					d.getPic2(), null);
	}
	
	//id로 검색
	public ArrayList<DiaryboardDto> getById(String id) {
		Member m = new Member(id,"","","","",null,"","","");
	    ArrayList<Diaryboard> list = dao.findByIdContaining(m);
	    ArrayList<DiaryboardDto> list2 = new ArrayList<DiaryboardDto>();
	    for (Diaryboard d : list) {
	        list2.add(new DiaryboardDto(d.getNum(), d.getTitle(), d.getContent(), d.getW_date(), d.getId(), d.getPic1(),
	                d.getPic2(), null));
	    }
	    return list2;
	}
	
	// 제목으로 검색
	public ArrayList<DiaryboardDto> getByTitle(String title) {
	    ArrayList<Diaryboard> list = dao.findByTitleContaining(title);
	    ArrayList<DiaryboardDto> list2 = new ArrayList<DiaryboardDto>();
	    for (Diaryboard d : list) {
	        list2.add(new DiaryboardDto(d.getNum(), d.getTitle(), d.getContent(), d.getW_date(), d.getId(), d.getPic1(),
	                d.getPic2(), null));
	    }
	    return list2;
	}
	
	//삭제
	public void delDiaryboard(int num) {
		dao.deleteById(num);
	}
}
