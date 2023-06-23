package com.example.demo.alarmtest.send;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.member.Member;
import com.example.demo.volboard.Volboard;
import com.example.demo.volboard.VolboardDto;

@Service
public class AlarmSenderService {
	
	@Autowired
	private AlarmSenderDao dao;

	/**
	 * 글 작성 및 수정
	 * @param dto
	 * @return
	 */
	public Alarmsender add(AlarmSenderDto dto) {
		Alarmsender alarmsender = dao.save(new Alarmsender(dto.getNum(), dto.getClicked()));
		return alarmsender;
	}
}