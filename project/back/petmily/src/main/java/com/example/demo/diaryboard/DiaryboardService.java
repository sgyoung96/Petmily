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
				dto.getId(), dto.getPic1(), dto.getPic2(), dto.getLikecnt()));
		return d.getNum();
	}

	// 상품 전체 검색
	public ArrayList<DiaryboardDto> getAll() {
		ArrayList<Diaryboard> list = (ArrayList<Diaryboard>) dao.findAllByOrderByNumDesc();
		ArrayList<DiaryboardDto> list2 = new ArrayList<DiaryboardDto>();
		for (Diaryboard d : list) {
			list2.add(new DiaryboardDto(d.getNum(), d.getTitle(), d.getContent(), d.getW_date(), d.getId(), d.getPic1(),
					d.getPic2(), d.getLikecnt(),null));
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
					d.getPic2(), d.getLikecnt(), null);
	}
	
	//id로 검색
	public ArrayList<DiaryboardDto> getById(String id) {
		Member m = new Member(id,"","","","",null,"","","",null);
	    ArrayList<Diaryboard> list = dao.findByIdContaining(m);
	    ArrayList<DiaryboardDto> list2 = new ArrayList<DiaryboardDto>();
	    for (Diaryboard d : list) {
	        list2.add(new DiaryboardDto(d.getNum(), d.getTitle(), d.getContent(), d.getW_date(), d.getId(), d.getPic1(),
	                d.getPic2(), d.getLikecnt(), null));
	    }
	    return list2;
	}
	
	// 제목으로 검색
	public ArrayList<DiaryboardDto> getByTitle(String title) {
	    ArrayList<Diaryboard> list = dao.findByTitleContaining(title);
	    ArrayList<DiaryboardDto> list2 = new ArrayList<DiaryboardDto>();
	    for (Diaryboard d : list) {
	        list2.add(new DiaryboardDto(d.getNum(), d.getTitle(), d.getContent(), d.getW_date(), d.getId(), d.getPic1(),
	                d.getPic2(), d.getLikecnt(),null));
	    }
	    return list2;
	}
	
	//좋아요 순으로 정렬
	public ArrayList<DiaryboardDto> getAllByLikecnt() {
		ArrayList<Diaryboard> list = (ArrayList<Diaryboard>) dao.findAllByOrderByLikecntDesc();
		ArrayList<DiaryboardDto> list2 = new ArrayList<DiaryboardDto>();
		for (Diaryboard d : list) {
			list2.add(new DiaryboardDto(d.getNum(), d.getTitle(), d.getContent(), d.getW_date(), d.getId(), d.getPic1(),
					d.getPic2(), d.getLikecnt(),null));
		}
		return list2;
	}
	
	//삭제
	public void delDiaryboard(int num) {
		dao.deleteById(num);
	}
	
	//좋아요 수 올림
	public void uplike(int num) {
		dao.upCount(num);
	}
	
	//좋아요 수 내림
	public void downlike(int num) {
		dao.downCount(num);
	}
	
	public int getCount() {
		return dao.countByAll();
	}
	//작성한 게시판수 출력
		public int printPerson(String id) {
			Member m = new Member(id,"","","","",null,"","","",null);
			return dao.countById(m);
		}
}
