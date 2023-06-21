package com.example.demo.participants;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.member.Member;
import com.example.demo.volboard.Volboard;

@Service
public class ParticipantsService {
	@Autowired
	private ParticipantsDao dao;
	
	//봉사참여자 등록
	public Member save(ParticipantsDto dto) {
		Participants p = dao.save(new Participants(dto.getNum(),dto.getBoardnum(),dto.getId()));
		return p.getId();
	}
	
	//봉사가 끝나거나 취소하면 데이터 삭제
	public void delPerson(String id, int boardnum) {
		Member m = new Member(id,"","","","",null,"","","");
		Volboard b = new Volboard(boardnum,null,"","",null,0,null,"","","",0);
		dao.deleteByIdAndBoardnum(m, b);
	}
	
	//현재 봉사모집인원 출력
	public int printPerson(int boardNum) {
		//Volboard vb = new Volboard(boardNum,null,"","",null,0,null,"","","",0);
		return dao.countByBoardNum(boardNum);
	}
	
	//봉사게시판에 중복신청 검사
	public ArrayList<ParticipantsDto> getByIdAndNum(String id, int boardnum) {
		Member m = new Member(id,"","","","",null,"","","");
		Volboard b = new Volboard(boardnum,null,"","",null,0,null,"","","",0);
		ArrayList<Participants> list = dao.findByIdAndBoardnum(m, b);
		ArrayList<ParticipantsDto> dlist = new ArrayList<>();
		for(Participants p : list) {
			dlist.add(new ParticipantsDto(p.getNum(), p.getBoardnum(), p.getId()));
		}
		return dlist;
	}
	
}
