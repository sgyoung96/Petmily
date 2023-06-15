package com.example.demo.message;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/message")
public class MessageController {
	@Autowired
	private MessageService service;
	
	//메세지 작성
	@PostMapping("")
	public Map sendMessage(MessageDto dto) {
		boolean flag = true;
		MessageDto dto2 = null;
		try {
		dto2 = service.save(dto);
		}catch(Exception e) {
			flag = false;
		}
		Map map = new HashMap();
		map.put("dto", dto2);
		map.put("flag", flag);
		return map;
	}
	
//	//전체목록
//	@GetMapping("")
//	public Map get() {
//		ArrayList<MessageDto> list = service.getAll();
//		Map map = new HashMap();
//		map.put("list", list);
//		return map;
//	}
	
//	//읽은 메세지, 안읽은 메세지 목록
//	@GetMapping("/{loginId}/{check}")
//	public Map getByRecieverAndM_check(@PathVariable("loginId") String loginId, @PathVariable("check") int check) {
//		ArrayList<MessageDto> list = service.getByRecieverAndM_check(loginId, check);
//		Map map = new HashMap();
//		map.put("list", list);
//		return map;
//	}
//	
	//sender로 검색
	@GetMapping("/sender/{sender}")
	public Map getBySender(@PathVariable("sender") String sender) {
		System.out.println(sender);
		ArrayList<MessageDto> list = service.getBySender(sender);
		Map map = new HashMap();
		map.put("list", list);
		return map;
	}
	
	//받은메세지 전체 목록
	@GetMapping("/reciever/{loginId}")
	public Map getByReciever(@PathVariable("loginId") String loginId) {
		ArrayList<MessageDto> list = service.getByReciever(loginId);
		Map map = new HashMap();
		map.put("list", list);
		return map;
	}
	
	//메세지 읽었을 때
	@PatchMapping("/{num}") //patch: 일부 컬럼 수정
	public Map check(@PathVariable("num") int num) {
		boolean flag = true;
		try {
			service.check(num);
		}catch(Exception e) {
			flag = false;
		}
		Map map = new HashMap();
		map.put("flag", flag);
		return map;
	}
}
