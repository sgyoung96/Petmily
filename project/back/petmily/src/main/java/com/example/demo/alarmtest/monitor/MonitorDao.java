package com.example.demo.alarmtest.monitor;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Repository;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Repository
public class MonitorDao {
	// 유저ID를 키로 SseEmitter를 해시맵에 저장할 수 있도록 구현했다.
    private Map<String, SseEmitter> emitterMap = new HashMap<>();

    public SseEmitter save(int num, SseEmitter sseEmitter) {
        emitterMap.put(getKey(num), sseEmitter);
        log.info("Saved SseEmitter for {}", num);
        return sseEmitter;
    }

    public Optional<SseEmitter> get(int num) {
        log.info("Got SseEmitter for {}", num);
        return Optional.ofNullable(emitterMap.get(getKey(num)));
    }

    public void delete(int num) {
        emitterMap.remove(getKey(num));
        log.info("Deleted SseEmitter for {}", num);
    }

    private String getKey(int num) {
        return "Emitter:UID:" + num;
    }
}	
