package com.example.demo.volboard;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.member.Member;

@Service
public class VolboardService {
	@Autowired
	private VolboardDao dao;
	
	//봉사모집게시판 생성 및 수정
	public int save(VolboardDto dto) {
		Volboard b = dao.save(new Volboard(dto.getNum(), dto.getWriter(), dto.getTitle(), dto.getContent(), dto.getVol_date(), dto.getVol_number(), dto.getW_date(), dto.getAddress(), dto.getDeadline(), dto.getPlace(), dto.getPic1(), dto.getPic2(),dto.getCount()));
		return b.getNum();
	}
	
	public VolboardDto getById(int num) {
		Volboard v = dao.findById(num).orElse(null);
		if(v == null) {
			return null;
		}
		return new VolboardDto(v.getNum(), v.getWriter(), v.getTitle(), v.getContent(), v.getVol_date(), v.getVol_number(), v.getW_date(), v.getAddress(), v.getDeadline(), v.getPlace(), v.getPic1(), v.getPic2(),v.getCount(),null);
	}
	
	//봉사모집게시판 전체검색
	public ArrayList<VolboardDto> getAll() {
		ArrayList<Volboard> list = (ArrayList<Volboard>) dao.findAll();
		ArrayList<VolboardDto> dtolist = new ArrayList<>();
		for(Volboard v : list) {
			dtolist.add(new VolboardDto(v.getNum(), v.getWriter(), v.getTitle(), v.getContent(), v.getVol_date(), v.getVol_number(), v.getW_date(), v.getAddress(), v.getDeadline(), v.getPlace(), v.getPic1(), v.getPic2(),v.getCount(),null));
		}
		return dtolist;
	}
	
	//봉사모집게시판 제목으로 검색
	public ArrayList<VolboardDto> getByTitle(String title){
		ArrayList<Volboard> list = dao.findByTitle(title);
		ArrayList<VolboardDto> dtolist = new ArrayList<>();
		for(Volboard v : list) {
			dtolist.add(new VolboardDto(v.getNum(), v.getWriter(), v.getTitle(), v.getContent(), v.getVol_date(), v.getVol_number(), v.getW_date(), v.getAddress(), v.getDeadline(), v.getPlace(), v.getPic1(), v.getPic2(),v.getCount(),null));
		}
		return dtolist;
	}
	
	//작성자로 검색
	public ArrayList<VolboardDto> getByAddress(String address) {
	     List<Volboard> list = dao.findByAddressContaining(address);
	    ArrayList<VolboardDto> dtolist = new ArrayList<>();
	    if (list != null) {
	        for (Volboard v : list) {
	            dtolist.add(new VolboardDto(v.getNum(), v.getWriter(), v.getTitle(), v.getContent(), v.getVol_date(), v.getVol_number(), v.getW_date(), v.getAddress(), v.getDeadline(), v.getPlace(), v.getPic1(), v.getPic2(), v.getCount(), null));
	        }
	    }
	    return dtolist;
	}

	//봉사모집게시판 삭제
	public void delBoard(int num) {
		dao.deleteById(null);
	}
	
	public void upCnt(int num) {
		dao.updateCount(num);
	}
	
	public void downCnt(int num) {
		dao.updateCount2(num);
	}
	
}
