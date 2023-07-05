package com.example.demo.message;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.member.Member;
import com.example.demo.member.MemberDao;

@Service
public class MessageService {

	@Autowired
	private MessageDao dao;

	@Autowired
	private MemberDao mdao;

	// 메세지 작성
	public MessageDto save(MessageDto dto) {

		Message entity = dao
				.save(new Message(dto.getNum(), dto.getSender(), dto.getReciever(), dto.getSend_dt(), dto.getTitle(),
						dto.getContent(), dto.getCheck(), dto.getAvailablesender(), dto.getAvailablereciever()));
		return new MessageDto(entity.getNum(), entity.getSender(), entity.getReciever(), entity.getSend_dt(),
				entity.getTitle(), entity.getContent(), entity.getCheck(), entity.getAvailablesender(),
				entity.getAvailablereciever());
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

	// 내가 받은 메세지 중 읽은 메세지, 안읽은 메세지 검색(삭제메세지 제외)
	public ArrayList<MessageDto> getByRecieverAndCheckAndAvailablereciever(String loginId, int check) { // check=0 : 안읽은
																										// 메세지, check=1:
																										// 읽은 메세지
		Member member = new Member(loginId, "", "", "", "", null, "", "", "", "");
		ArrayList<Message> list = (ArrayList<Message>) dao.findByRecieverAndCheckAndAvailablereciever(member, check,
				"A");
		ArrayList<MessageDto> list2 = new ArrayList<MessageDto>();
		for (Message m : list) {
			list2.add(new MessageDto(m.getNum(), m.getSender(), m.getReciever(), m.getSend_dt(), m.getTitle(),
					m.getContent(), m.getCheck(), m.getAvailablesender(), m.getAvailablereciever()));
		}
		return list2;
	}

	// 내가 보낸 메세지 중 수신자가 읽은 메세지, 안읽은 메세지 검색(삭제메세지 제외)
	public ArrayList<MessageDto> getBySenderAndCheckAndAvailablesender(String loginId, int check) { // check=0 : 안읽은
																									// 메세지, check=1: 읽은
																									// 메세지
		Member member = new Member(loginId, "", "", "", "", null, "", "", "", "");
		ArrayList<Message> list = (ArrayList<Message>) dao.findBySenderAndCheckAndAvailablesender(member, check, "A");
		ArrayList<MessageDto> list2 = new ArrayList<MessageDto>();
		for (Message m : list) {
			list2.add(new MessageDto(m.getNum(), m.getSender(), m.getReciever(), m.getSend_dt(), m.getTitle(),
					m.getContent(), m.getCheck(), m.getAvailablesender(), m.getAvailablereciever()));
		}
		return list2;
	}

	// 내가보낸메세지(sender) or 보낸이로 검색
	public ArrayList<MessageDto> getBySender(String id) {
		Member member = new Member(id, "", "", "", "", null, "", "", "", "");
		ArrayList<Message> list = (ArrayList<Message>) dao.findBySenderAndAvailablesender(member, "A");
		ArrayList<MessageDto> list2 = new ArrayList<MessageDto>();
		for (Message m : list) {
			list2.add(new MessageDto(m.getNum(), m.getSender(), m.getReciever(), m.getSend_dt(), m.getTitle(),
					m.getContent(), m.getCheck(), m.getAvailablesender(), m.getAvailablereciever()));
		}
		return list2;
	}

	// 내가 받은 메세지 목록(reciever)
	public ArrayList<MessageDto> getByReciever(String loginId) {
		System.out.println("service reciever");
		Member member = new Member(loginId, "", "", "", "", null, "", "", "", "");
		ArrayList<Message> list = (ArrayList<Message>) dao.findByRecieverAndAvailablereciever(member, "A");
		System.out.println("Service reciever : " + list);
		ArrayList<MessageDto> list2 = new ArrayList<MessageDto>();
		for (Message m : list) {
			list2.add(new MessageDto(m.getNum(), m.getSender(), m.getReciever(), m.getSend_dt(), m.getTitle(),
					m.getContent(), m.getCheck(), m.getAvailablesender(), m.getAvailablereciever()));
		}
		return list2;
	}

	// 받은 메세지 제목으로 검색
	public List<MessageDto> getByTitleRecieve(String loginId, String title) {
		System.out.println("getbytitle");
		System.out.println("loginId : " + loginId);
		System.out.println("title : " + title);
//		Member member = new Member(loginId, "", "", "", "", null, "", "", "","");
//		
//		ArrayList<Message> list = (ArrayList<Message>)dao.findByRecieverAndTitleContainingAndAvailablereciever(member, title, "A");
//		ArrayList<MessageDto> list2 = new ArrayList<MessageDto>();
//		for(Message m:list) {
//			list2.add(new MessageDto(m.getNum(),m.getSender(),m.getReciever(),m.getSend_dt(),m.getTitle(),m.getContent(),m.getCheck(),m.getAvailablesender(),m.getAvailablereciever()));
//		}
//		return list2;

		Member member = mdao.findById(loginId).orElse(null);

		List<Message> list = dao.findByTitleRecieve(member, title, "A");
		List<MessageDto> list2 = new ArrayList<>();
		for (Message m : list) {
			list2.add(new MessageDto(m.getNum(), m.getSender(), m.getReciever(), m.getSend_dt(), m.getTitle(),
					m.getContent(), m.getCheck(), m.getAvailablesender(), m.getAvailablereciever()));
		}
		return list2;
	}

	// 보낸 메세지 제목으로 검색
	public List<MessageDto> getByTitleSend(String loginId, String title) {

		Member member = mdao.findById(loginId).orElse(null);

		List<Message> list = dao.findByTitleSend(member, title, "A");
		List<MessageDto> list2 = new ArrayList<>();
		for (Message m : list) {
			list2.add(new MessageDto(m.getNum(), m.getSender(), m.getReciever(), m.getSend_dt(), m.getTitle(),
					m.getContent(), m.getCheck(), m.getAvailablesender(), m.getAvailablereciever()));
		}
		return list2;
	}

	// 메세지 읽었을 때
	public void check(int num) {
		dao.updatem_check(num);
	}

	// sender메세지 삭제
	public void delMessagesender(int num) {
		dao.updateavailablesender(num);
	}

	// reciever메세지 삭제
	public void delMessagereciever(int num) {
		dao.updateavailablereciever(num);
	}

	// db메세지 삭제
	public void delMessage(int num) {
		Optional<Message> message = dao.findById(num);
		if (message.isPresent()) {
			Message m = message.get();
			if (m.getAvailablereciever().equals("N") && m.getAvailablesender().equals("N")) {

				dao.deleteById(num);
			}
		}
	}

	// 받은 쪽지수 출력
	public int printPerson(String id) {
		Member m = new Member(id, "", "", "", "", null, "", "", "", null);
		return dao.countById(m);
	}
}
