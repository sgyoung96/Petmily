package com.example.demo.alarmtest.monitor;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.member.Member;
import com.example.demo.volboard.Volboard;
import com.example.demo.volboard.VolboardDto;

@Service
public class MonitorService {
	
	@Autowired
	private MonitorDao dao;
	
	/**
	 * 전체 목록 조회
	 * @return
	 */
	public ArrayList<MonitorDto> getAll() {
		ArrayList<Monitor> list = (ArrayList<Monitor>) dao.findAll();
		ArrayList<MonitorDto> list2 = new ArrayList<MonitorDto>();
		for (Monitor monitor : list) {
			list2.add(new MonitorDto(monitor.getNum(), monitor.getContent()));
		}
		return list2;
	}
}