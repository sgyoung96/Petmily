package com.example.demo.adoptcomment;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.adopt.Adoptboard;
import com.example.demo.diarycomment.Diarycomment;
import com.example.demo.diarycomment.DiarycommentDto;
import com.example.demo.member.Member;

@Service
public class AdoptCommentService {
	
	@Autowired
	private AdoptCommentDao dao;
	
	/**
	 * 해당 게시글 댓글 전체 조회
	 * @param num
	 * @return
	 */
	public ArrayList<AdoptCommentDto> getAll(int num) {
		Adoptboard b = new Adoptboard(num,null,"","","","",null,"","","",0,0,0);
		ArrayList<Adoptcomment> list = dao.findByNum(b);
		ArrayList<AdoptCommentDto> list2 = new ArrayList<AdoptCommentDto>();
		
		for (Adoptcomment adoptComment : list) {
			list2.add(new AdoptCommentDto(adoptComment.getAb_num(), adoptComment.getContent(), adoptComment.getId(), adoptComment.getW_date(), adoptComment.getNum()));
		}
		return list2;
	}
	
	/**
	 * 댓글 입력, 수정
	 * @param dtoz
	 * @return
	 */
	
		public AdoptCommentDto save(AdoptCommentDto dto) {
			Adoptcomment c = dao.save(new Adoptcomment(dto.getAb_num(), dto.getContent(), dto.getId(), dto.getW_date(), dto.getNum()));
			return new AdoptCommentDto(c.getAb_num(), c.getContent(), c.getId(), c.getW_date(), c.getNum());
		}
	
	/**
	 * 댓글 삭제
	 * @param num
	 */
	public void remove(int ab_num) {
		dao.deleteById(ab_num);
	}
	
	//작성한 댓글수 출력
	public int printPerson(String id) {
		Member m = new Member(id,"","","","",null,"","","",null);
		return dao.countById(m);
	}
	
	//pk로 검색
		public AdoptCommentDto getByNum2(int db_num) {
			Adoptcomment c = dao.findById(db_num).orElse(null);
			if(c==null) {
				return null;
			}
			return new AdoptCommentDto(c.getAb_num(), c.getContent(), c.getId(), c.getW_date(), c.getNum());
		}
}