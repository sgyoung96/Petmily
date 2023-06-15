package com.example.demo.participants;

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
		Participants p = dao.save(new Participants(dto.getNum(),dto.getBoard_num(),dto.getId()));
		return p.getId();
	}
	
	//봉사가 끝나거나 취소하면 데이터 삭제
	public void delPerson(int num) {
		dao.deleteById(num);
	}
	
	//현재 봉사모집인원 출력
	public long printPerson(int boardNum) {
		//Volboard vb = new Volboard(boardNum,null,"","",null,0,null,"","","",0);
		return dao.countByBoardNum(boardNum);
	}
	
}
