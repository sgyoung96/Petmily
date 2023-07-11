package com.example.demo.watchlist;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.example.demo.diaryboard.Diaryboard;
import com.example.demo.diaryboard.DiaryboardDto;
import com.example.demo.member.Member;
import com.example.demo.volboard.Volboard;

@Service
public class WatchlistService {
	@Autowired
	private WatchlistDao dao;
	
	WatchlistDto getDuplicate(String id, int num) {
		Member m = new Member(id,"","","","",null,"","","",null);
		Volboard b = new Volboard(num,null,"","",null,0,null,"",null,"","","",0,0);
		Watchlist dto = dao.findByIdAndNum(m, b);
		if(dto == null) {
			return null;
		}else {
			return new WatchlistDto(dto.getDb_num(), dto.getId(), dto.getNum());
		}
	}
	
	ArrayList<WatchlistDto> getById(String id){
		Member m = new Member(id,"","","","",null,"","","",null);
		ArrayList<Watchlist> list = dao.findById(m);
		ArrayList<WatchlistDto> list2 = new ArrayList<>();
		for (Watchlist d : list) {
			list2.add(new WatchlistDto(d.getDb_num(), d.getId(), d.getNum()));
		}
		return list2;
		
	}
	
	
	public void save(WatchlistDto dto) {
		dao.save(new Watchlist(dto.getDb_num(), dto.getId(), dto.getNum()));
	}
	
	public void delete(WatchlistDto dto) {
		dao.delete(new Watchlist(dto.getDb_num(), dto.getId(), dto.getNum()));
	}
	
	//관심수 출력
	public int printPerson(String id) {
		Member m = new Member(id,"","","","",null,"","","",null);
		return dao.countById(m);
	}
}
