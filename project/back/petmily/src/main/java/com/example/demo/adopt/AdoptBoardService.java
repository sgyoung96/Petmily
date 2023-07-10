package com.example.demo.adopt;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.diaryboard.Diaryboard;
import com.example.demo.diaryboard.DiaryboardDto;
import com.example.demo.member.Member;
import com.example.demo.volboard.Volboard;
import com.example.demo.volboard.VolboardDto;

@Service
public class AdoptBoardService {

	@Autowired
	private AdoptBoardDao dao;

	/**
	 * 전체 목록 조회
	 * 
	 * @return
	 */
	public ArrayList<AdoptBoardDto> getAll() {
		ArrayList<Adoptboard> list = (ArrayList<Adoptboard>) dao.findAll();
		ArrayList<AdoptBoardDto> list2 = new ArrayList<AdoptBoardDto>();
		for (Adoptboard adoptBoard : list) {
			list2.add(new AdoptBoardDto(adoptBoard.getNum(), adoptBoard.getId(), adoptBoard.getTitle(),
					adoptBoard.getContent(), adoptBoard.getCategory(), adoptBoard.getGender(), adoptBoard.getW_date(),
					adoptBoard.getAddress(), adoptBoard.getPic1(), adoptBoard.getPic2(), adoptBoard.getLikecnt(),
					adoptBoard.getCnt(), adoptBoard.getIscheck(), null));
		}
		return list2;
	}

	/**
	 * 좋아요 순으로 전체 목록 조회
	 * 
	 * @return
	 */
	public ArrayList<AdoptBoardDto> getAllOl() {
		ArrayList<Adoptboard> list = (ArrayList<Adoptboard>) dao.findAllByOrderByLikecntDesc();
		ArrayList<AdoptBoardDto> list2 = new ArrayList<AdoptBoardDto>();
		for (Adoptboard adoptBoard : list) {
			list2.add(new AdoptBoardDto(adoptBoard.getNum(), adoptBoard.getId(), adoptBoard.getTitle(),
					adoptBoard.getContent(), adoptBoard.getCategory(), adoptBoard.getGender(), adoptBoard.getW_date(),
					adoptBoard.getAddress(), adoptBoard.getPic1(), adoptBoard.getPic2(), adoptBoard.getLikecnt(),
					adoptBoard.getCnt(), adoptBoard.getIscheck(), null));
		}
		return list2;
	}

	/**
	 * 디테일 조회
	 * 
	 * @param num
	 * @return
	 */
	public AdoptBoardDto getDetail(int num) {
		Adoptboard adoptBoard = dao.findById(num).orElse(null);
		if (adoptBoard == null) {
			return null;
		}
		return new AdoptBoardDto(adoptBoard.getNum(), adoptBoard.getId(), adoptBoard.getTitle(),
				adoptBoard.getContent(), adoptBoard.getCategory(), adoptBoard.getGender(), adoptBoard.getW_date(),
				adoptBoard.getAddress(), adoptBoard.getPic1(), adoptBoard.getPic2(), adoptBoard.getLikecnt(),
				adoptBoard.getCnt(), adoptBoard.getIscheck(), null);
	}

	/**
	 * 글 작성 및 수정
	 * 
	 * @param dto
	 * @return
	 */
	public int add(AdoptBoardDto dto) {
		Adoptboard adoptBoard = dao.save(new Adoptboard(dto.getNum(), dto.getId(), dto.getTitle(), dto.getContent(),
				dto.getCategory(), dto.getGender(), dto.getW_date(), dto.getAddress(), dto.getPic1(), dto.getPic2(),
				dto.getLikecnt(), dto.getCnt(), dto.getIscheck()));
		return adoptBoard.getNum();
	}

	/**
	 * 글 삭제
	 * 
	 * @param num
	 */
	public void remove(int num) {
		dao.deleteById(null);
		;
	}

	/**
	 * 작성자로 검색
	 * 
	 * @param id
	 */
	public ArrayList<AdoptBoardDto> getByWriter(String id) {
		Member m = new Member(id, "", "", "", "", null, "", "", "", null);
		ArrayList<Adoptboard> list = dao.findById(m);
		ArrayList<AdoptBoardDto> dtolist = new ArrayList<>();
		for (Adoptboard v : list) {
			dtolist.add(new AdoptBoardDto(v.getNum(), v.getId(), v.getTitle(), v.getContent(), v.getCategory(),
					v.getGender(), v.getW_date(), v.getAddress(), v.getPic1(), v.getPic2(), v.getLikecnt(), v.getCnt(),
					v.getIscheck(), null));
		}
		return dtolist;
	}
	
	public ArrayList<AdoptBoardDto> getByAddress(String address) {
		ArrayList<Adoptboard> list = dao.findByAddressContaining(address);
		ArrayList<AdoptBoardDto> dtolist = new ArrayList<AdoptBoardDto>();
		for (Adoptboard v : list) {
			dtolist.add(new AdoptBoardDto(v.getNum(), v.getId(), v.getTitle(), v.getContent(), v.getCategory(),
					v.getGender(), v.getW_date(), v.getAddress(), v.getPic1(), v.getPic2(), v.getLikecnt(), v.getCnt(),
					v.getIscheck(), null));
		}
		return dtolist;
	}

	// 좋아요 수 올림
	public void uplike(int num) {
		dao.upCount(num);
	}

	// 좋아요 수 내림
	public void downlike(int num) {
		dao.downCount(num);
	}

	public int getCount() {
		return dao.countByAll();
	}

	// 작성한 게시판수 출력
	public int printPerson(String id) {
		Member m = new Member(id, "", "", "", "", null, "", "", "", null);
		return dao.countById(m);
	}

	// 조회수 올림
	public void upCnt2(int num) {
		dao.updateCnt(num);
	}

	// 분양 완료로 바뀜
	public void upIsCheck(int num) {
		dao.upIsCheck(num);
	}

	// 분양 완료로 바뀜
	public void downIsCheck(int num) {
		dao.downIsCheck(num);
	}
}