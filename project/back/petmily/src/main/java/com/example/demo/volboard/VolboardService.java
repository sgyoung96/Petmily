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
		Volboard b = dao.save(new Volboard(dto.getNum(), dto.getWriter(), dto.getTitle(), dto.getContent(), dto.getVol_date(), dto.getVol_number(), dto.getW_date(), dto.getAddress(), dto.getDeadline(), dto.getPlace(), dto.getPic1(), dto.getPic2(),dto.getCount(),dto.getCnt()));
		return b.getNum();
	}
	
	public VolboardDto getById(int num) {
		Volboard v = dao.findById(num).orElse(null);
		if(v == null) {
			return null;
		}
		return new VolboardDto(v.getNum(), v.getWriter(), v.getTitle(), v.getContent(), v.getVol_date(), v.getVol_number(), v.getW_date(), v.getAddress(), v.getDeadline(), v.getPlace(), v.getPic1(), v.getPic2(),v.getCount(),v.getCnt(),null);
	}
	
	//봉사모집게시판 전체검색
	public ArrayList<VolboardDto> getAll() {
		ArrayList<Volboard> list = (ArrayList<Volboard>) dao.findAll();
		ArrayList<VolboardDto> dtolist = new ArrayList<>();
		for(Volboard v : list) {
			dtolist.add(new VolboardDto(v.getNum(), v.getWriter(), v.getTitle(), v.getContent(), v.getVol_date(), v.getVol_number(), v.getW_date(), v.getAddress(), v.getDeadline(), v.getPlace(), v.getPic1(), v.getPic2(),v.getCount(),v.getCnt(),null));
		}
		return dtolist;
	}
	
	//조회수 순으로 봉사모집게시판 전체검색
		public ArrayList<VolboardDto> getAllByol() {
			ArrayList<Volboard> list = (ArrayList<Volboard>) dao.findAllByOrderByCnt();
			ArrayList<VolboardDto> dtolist = new ArrayList<>();
			for(Volboard v : list) {
				dtolist.add(new VolboardDto(v.getNum(), v.getWriter(), v.getTitle(), v.getContent(), v.getVol_date(), v.getVol_number(), v.getW_date(), v.getAddress(), v.getDeadline(), v.getPlace(), v.getPic1(), v.getPic2(),v.getCount(),v.getCnt(),null));
			}
			return dtolist;
		}
	
	//봉사모집게시판 제목으로 검색
	public ArrayList<VolboardDto> getByTitle(String title){
		ArrayList<Volboard> list = dao.findByTitle(title);
		ArrayList<VolboardDto> dtolist = new ArrayList<>();
		for(Volboard v : list) {
			dtolist.add(new VolboardDto(v.getNum(), v.getWriter(), v.getTitle(), v.getContent(), v.getVol_date(), v.getVol_number(), v.getW_date(), v.getAddress(), v.getDeadline(), v.getPlace(), v.getPic1(), v.getPic2(),v.getCount(),v.getCnt(),null));
		}
		return dtolist;
	}
	
	//작성자로 검색
	public ArrayList<VolboardDto> getByAddress(String address) {
	     List<Volboard> list = dao.findByAddressContaining(address);
	    ArrayList<VolboardDto> dtolist = new ArrayList<>();
	    if (list != null) {
	        for (Volboard v : list) {
	            dtolist.add(new VolboardDto(v.getNum(), v.getWriter(), v.getTitle(), v.getContent(), v.getVol_date(), v.getVol_number(), v.getW_date(), v.getAddress(), v.getDeadline(), v.getPlace(), v.getPic1(), v.getPic2(), v.getCount(),v.getCnt(),null));
	        }
	    }
	    return dtolist;
	}

	//봉사모집게시판 삭제
	public void delBoard(int num) {
		dao.deleteById(num);
	}
	
	// 봉사모집게시판 참가자 수 +1
	public void upCnt(int num) {
		dao.updateCount(num);
	}
	
	// 봉사모집게시판 참가자 수 -1
	public void downCnt(int num) {
		dao.updateCount2(num);
	}
	
	// 전체 행수 출력
	public int getCount() {
		return dao.countByAll();
	}
	
	//조회수 올림
	public void upCnt2(int num) {
		dao.updateCnt(num);
	}
	
	//작성한 게시판수 출력
		public int printPerson(String id) {
			Member m = new Member(id,"","","","",null,"","","",null);
			return dao.countById(m);
		}
	
}
