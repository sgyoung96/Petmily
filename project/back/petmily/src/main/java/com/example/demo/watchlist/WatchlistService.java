package com.example.demo.watchlist;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
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
	
	public void save(WatchlistDto dto) {
		dao.save(new Watchlist(dto.getDb_num(), dto.getId(), dto.getNum()));
	}
	
	public void delete(WatchlistDto dto) {
		dao.delete(new Watchlist(dto.getDb_num(), dto.getId(), dto.getNum()));
	}
}
