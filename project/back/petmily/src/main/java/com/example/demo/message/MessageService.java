package com.example.demo.message;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.member.Member;



@Service
public class MessageService {
	
	@Autowired
	private MessageDao dao;
	
	
	//메세지 작성
	public MessageDto save(MessageDto dto) {
		Message entity = dao.save(new Message(dto.getNum(), dto.getSender(),dto.getReciever(),dto.getSend_dt(),dto.getTitle(),dto.getContent(),dto.getM_check()));
	return new MessageDto(entity.getNum(), entity.getSender(), entity.getReciever(),entity.getSend_dt(),entity.getTitle(),entity.getContent(),entity.getM_check());
	}
	
//	//메세지 전체
//	public ArrayList<MessageDto> getAll(){
//		ArrayList<Message> list = (ArrayList<Message>)dao.findAll();
//		ArrayList<MessageDto> list2 = new ArrayList<MessageDto>();
//		for(Message m:list) {
//			list2.add(new MessageDto(m.getNum(),m.getSender(),m.getReciever(),m.getSend_dt(),m.getTitle(),m.getContent(),m.getM_check()));
//		}
//		return list2;
//	}
	
//	//읽은 메세지, 안읽은 메세지 목록
//	public ArrayList<MessageDto> getByRecieverAndM_check(String loginId, int check){
//		Member member = new Member(loginId, "", "", "", "", null, "", "", "");
//		ArrayList<Message> list = (ArrayList<Message>)dao.findByRecieverAndM_check(member, check);
//		ArrayList<MessageDto> list2 = new ArrayList<MessageDto>();
//		for(Message m:list) {
//			list2.add(new MessageDto(m.getNum(),m.getSender(),m.getReciever(),m.getSend_dt(),m.getTitle(),m.getContent(),m.getM_check()));
//		}
//		return list2;
//	}
	
	//보낸이로 검색(sender)
	public ArrayList<MessageDto> getBySender(String id){
		Member member = new Member(id, "", "", "", "", null, "", "", "");
		ArrayList<Message> list = (ArrayList<Message>)dao.findBySender(member);
		ArrayList<MessageDto> list2 = new ArrayList<MessageDto>();
		for(Message m:list) {
			list2.add(new MessageDto(m.getNum(),m.getSender(),m.getReciever(),m.getSend_dt(),m.getTitle(),m.getContent(),m.getM_check()));
		}
		return list2;
	}
	
	//받은 메세지 목록(reciever)
	public ArrayList<MessageDto> getByReciever(String loginId){
		Member member = new Member(loginId, "", "", "", "", null, "", "", "");
		ArrayList<Message> list = (ArrayList<Message>)dao.findByReciever(member);
		ArrayList<MessageDto> list2 = new ArrayList<MessageDto>();
		for(Message m:list) {
			list2.add(new MessageDto(m.getNum(),m.getSender(),m.getReciever(),m.getSend_dt(),m.getTitle(),m.getContent(),m.getM_check()));
		}
		return list2;
	}
	
	//메세지 읽었을 때
	public void check(int num) {
		dao.updatem_check(num);
	}
	
	//메세지 삭제
	public void delMessage(int num) {
		dao.deleteById(num);
	}
}
