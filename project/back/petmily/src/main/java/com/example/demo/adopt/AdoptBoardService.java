package com.example.demo.adopt;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdoptBoardService {
	
	@Autowired
	private AdoptBoardDao dao;
	
	/**
	 * 전체 목록 조회
	 * @return
	 */
	public ArrayList<AdoptBoardDto> getAll() {
		ArrayList<Adoptboard> list = (ArrayList<Adoptboard>) dao.getAll();
		ArrayList<AdoptBoardDto> list2 = new ArrayList<AdoptBoardDto>();
		for (Adoptboard adoptBoard : list) {
			list2.add(new AdoptBoardDto(adoptBoard.getNum(), adoptBoard.getId(), adoptBoard.getTitle(), adoptBoard.getContent(), adoptBoard.getW_date(), adoptBoard.getAddress(), adoptBoard.getPic1(), adoptBoard.getPic2(), null));
		}
		return list2;
	}
	
}
/*
private int num;		// 개인 분양 게시판 고유 번호 (PK)
private Member id;		// 작성자 (Member 의 id FK)
private String title;	// 제목
private String content;	// 내용
private Date w_date;	// 날짜
private String address;	// 주소
private String pic1;	// 첫번째 사진 파일 경로
private String pic2;	// 두번째 사진 파일 경로
*/