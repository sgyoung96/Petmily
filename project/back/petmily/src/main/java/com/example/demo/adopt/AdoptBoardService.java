package com.example.demo.adopt;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.member.Member;
import com.example.demo.volboard.Volboard;
import com.example.demo.volboard.VolboardDto;

@Service
public class AdoptBoardService {
	
	@Autowired
	private AdoptBoardDao dao;
	
	/**
	 * 전체 목록 조회
	 * @return
	 */
	public ArrayList<AdoptBoardDto> getAll() {
		ArrayList<Adoptboard> list = (ArrayList<Adoptboard>) dao.findAll();
		ArrayList<AdoptBoardDto> list2 = new ArrayList<AdoptBoardDto>();
		for (Adoptboard adoptBoard : list) {
			list2.add(new AdoptBoardDto(adoptBoard.getNum(), adoptBoard.getId(), adoptBoard.getTitle(), adoptBoard.getContent(), adoptBoard.getW_date(), adoptBoard.getAddress(), adoptBoard.getPic1(), adoptBoard.getPic2(), null));
		}
		return list2;
	}
	
	/**
	 * 디테일 조회
	 * @param num
	 * @return
	 */
	public AdoptBoardDto getDetail(int num) {
		Adoptboard adoptBoard = dao.findById(num).orElse(null);
		if (adoptBoard == null) {
			return null;
		}
		return new AdoptBoardDto(adoptBoard.getNum(), adoptBoard.getId(), adoptBoard.getTitle(), adoptBoard.getContent(), adoptBoard.getW_date(), adoptBoard.getAddress(), adoptBoard.getPic1(), adoptBoard.getPic2(), null);
	}
	
	/**
	 * 글 작성 및 수정
	 * @param dto
	 * @return
	 */
	public int add(AdoptBoardDto dto) {
		Adoptboard adoptBoard = dao.save(new Adoptboard(dto.getNum(), dto.getId(), dto.getTitle(), dto.getContent(), dto.getW_date(), dto.getAddress(), dto.getPic1(), dto.getPic2()));
		return adoptBoard.getNum();
	}
	
	/**
	 * 글 삭제
	 * @param num
	 */
	public void remove(int num) {
		dao.deleteById(null);;
	}
	
	/**
	 * 작성자로 검색
	 * @param id
	 */
	public ArrayList<AdoptBoardDto> getByWriter(String id) {
		Member m = new Member(id,"","","","",null,"","","");
		ArrayList<Adoptboard> list = dao.findById(m);
		ArrayList<AdoptBoardDto> dtolist = new ArrayList<>();
		for(Adoptboard v : list) {
			dtolist.add(new AdoptBoardDto(v.getNum(), v.getId(), v.getTitle(), v.getContent(), v.getW_date(), v.getAddress(), v.getPic1(), v.getPic2(), null));
		}
		return dtolist;
	}
}