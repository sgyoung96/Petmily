package com.example.demo.alarmtest.monitor;

import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@Service
@RequiredArgsConstructor
public class MonitorService {

	private final static Long DEFAULT_TIMEOUT = 3600000L;
	private final static String NOTIFICATION_NAME = "notify";

	private final MonitorDao dao;

	public SseEmitter connectNotification(int num) {
		// 새로운 SseEmitter를 만든다
		SseEmitter sseEmitter = new SseEmitter(DEFAULT_TIMEOUT);

		// 유저 ID로 SseEmitter를 저장한다.
		dao.save(num, sseEmitter);

		// 세션이 종료될 경우 저장한 SseEmitter를 삭제한다.
		sseEmitter.onCompletion(() -> dao.delete(num));
		sseEmitter.onTimeout(() -> dao.delete(num));

		// 503 Service Unavailable 오류가 발생하지 않도록 첫 데이터를 보낸다.
		try {
			sseEmitter.send(SseEmitter.event().id("").name(NOTIFICATION_NAME).data("Connection completed"));
		} catch (java.io.IOException e) {
			e.printStackTrace();
		}
		
		return sseEmitter;
	}
	
	public void send(int num, Long notificationId) {
        // 유저 ID로 SseEmitter를 찾아 이벤트를 발생 시킨다.
        dao.get(num).ifPresentOrElse(sseEmitter -> {
        	try {
				sseEmitter.send(SseEmitter.event().id(notificationId.toString()).name(NOTIFICATION_NAME).data("New notification"));
			} catch (java.io.IOException e) {
				// IOException이 발생하면 저장된 SseEmitter를 삭제하고 예외를 발생시킨다.
				dao.delete(num);
				e.printStackTrace();
			}
        }, () -> log.info("No emitter found"));
    }
}
