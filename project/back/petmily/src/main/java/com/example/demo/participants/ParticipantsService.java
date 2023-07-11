package com.example.demo.participants;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.member.Member;
import com.example.demo.volboard.Volboard;
import com.example.demo.watchlist.Watchlist;
import com.example.demo.watchlist.WatchlistDto;

@Service
public class ParticipantsService {
	@Autowired
	private ParticipantsDao dao;
	
	//봉사참여자 등록
	public Member save(ParticipantsDto dto) {
		Participants p = dao.save(new Participants(dto.getNum(),dto.getBoardnum(),dto.getId()));
		return p.getId();
	}
	
	ArrayList<ParticipantsDto> getById(String id){
		Member m = new Member(id,"","","","",null,"","","",null);
		ArrayList<Participants> list = dao.findById(m);
		ArrayList<ParticipantsDto> list2 = new ArrayList<>();
		for (Participants d : list) {
			list2.add(new ParticipantsDto(d.getNum(), d.getBoardnum(), d.getId()));
		}
		return list2;
		
	}
	
	//봉사가 끝나거나 취소하면 데이터 삭제
	public void delPerson(String id, int boardnum) {
		Member m = new Member(id,"","","","",null,"","","",null);
		Volboard b = new Volboard(boardnum,null,"","",null,0,null,"",null,"","","",0,0);
		dao.deleteByIdAndBoardnum(m, b);
	}
	
	//현재 봉사모집인원수 출력
	public int printPerson(int boardNum) {
		//Volboard vb = new Volboard(boardNum,null,"","",null,0,null,"","","",0);
		return dao.countByBoardNum(boardNum);
	}
	
	//모집인원 ID 출력
	public ArrayList<ParticipantsDto> getByNum(int boardNum){
		Volboard vb = new Volboard(boardNum,null,"","",null,0,null,"",null,"","","",0,0);
		ArrayList<Participants> list = dao.findByBoardnum(vb);
		ArrayList<ParticipantsDto> dlist = new ArrayList<>();
		for(Participants p : list) {
			dlist.add(new ParticipantsDto(p.getNum(), p.getBoardnum(), p.getId()));
		}
		return dlist;
	}
	
	//봉사게시판에 중복신청 검사
	public ArrayList<ParticipantsDto> getByIdAndNum(String id, int boardnum) {
		Member m = new Member(id,"","","","",null,"","","",null);
		Volboard b = new Volboard(boardnum, null,"","",null,0,null,"",null,"","","",0,0);
		ArrayList<Participants> list = dao.findByIdAndBoardnum(m, b);
		ArrayList<ParticipantsDto> dlist = new ArrayList<>();
		for(Participants p : list) {
			dlist.add(new ParticipantsDto(p.getNum(), p.getBoardnum(), p.getId()));
		}
		return dlist;
	}
	
}
