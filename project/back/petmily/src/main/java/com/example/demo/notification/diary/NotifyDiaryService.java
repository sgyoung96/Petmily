package com.example.demo.notification.diary;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NotifyDiaryService {
	
	@Autowired
	private NotifyDiaryDao dao;

	
	public ArrayList<NotifyDiaryDto> getAll(String id) {
		ArrayList<Notifydiary> list = new ArrayList<>();
		list = (ArrayList<Notifydiary>) dao.getAllDesc(id);
		ArrayList<NotifyDiaryDto> dtoList = new ArrayList<>();
		
		for (Notifydiary n : list) {
			dtoList.add(new NotifyDiaryDto(n.getNum(), n.getId(), n.getName(), n.getTitle(), n.getContent(), n.getInstTime(), n.getIsClicked()));
		}
		
		return dtoList;
	}
}