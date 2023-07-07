package com.example.demo.notification;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins="*")
@RequestMapping("/notify")
public class NotificationController {
	
	@Autowired
	private NotificationService service = new NotificationService();
	
	/**
	 * 새 알림 받는 사람으로 전체 검색
	 * @param id
	 * @return
	 */
	@GetMapping("/new/{id}")
	public Map getNewNotification(@PathVariable("id") String id) {
		ArrayList<NotificationDto> dto = service.getNewNotification(id);
		Map result = new HashMap();
		result.put("list", dto);
		return result;
	}
	
	/**
	 * 모든 알림 받는 사람으로 전체 검색
	 * @param id
	 * @return
	 */
	@GetMapping("/all/{id}")
	public Map getAllNotification(@PathVariable("id") String id) {
		ArrayList<NotificationDto> dto = service.getAllNotification(id);
		Map result = new HashMap();
		result.put("list", dto);
		return result;
	}
	
	/**
	 * 분양해요 게시판 댓글 알림
	 * @param id
	 * @return
	 */
	@GetMapping("/adopt/{id}")
	public Map getAdoptCommentNotification(@PathVariable("id") String id) {
		ArrayList<NotificationDto> dto = service.getAdoptCommentNotification(id);
		Map result = new HashMap();
		result.put("list", dto);
		return result;
	}
	
	/**
	 * 입양일지 게시판 댓글 알림
	 * @param id
	 * @return
	 */
	@GetMapping("/diary/{id}")
	public Map getDiaryCommentNotification(@PathVariable("id") String id) {
		ArrayList<NotificationDto> dto = service.getDiaryCommentNotification(id);
		Map result = new HashMap();
		result.put("list", dto);
		return result;
	}
	
	/**
	 * 쪽지함 받은 쪽지 알림
	 * @param id
	 * @return
	 */
	@GetMapping("/message/{id}")
	public Map getMessageNotification(@PathVariable("id") String id) {
		ArrayList<NotificationDto> dto = service.getMessageNotification(id);
		Map result = new HashMap();
		result.put("list", dto);
		return result;
	}
	
	
	@PostMapping("/type")
	public void setNotifyTypeOpened(@PathVariable("id") Map map) {
		String id = (String) map.get("id");
		ArrayList<Integer> numList = (ArrayList<Integer>) map.get("numList");
		
		service.setNotifyTypeOpened(id, numList);
	}
	
	@PostMapping("/read/{num}")
	public void readNotification(@PathVariable("num") int num) {
		service.readNotification(num);
	}
}
