package com.example.demo.alarmtest.monitor;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import lombok.RequiredArgsConstructor;


@RestController
@CrossOrigin(origins= "*")
@RequestMapping("/monitor")
@RequiredArgsConstructor
public class MonitorController {
	
	@Autowired
	private MonitorService service;
	
	@GetMapping("/{num}")
	public SseEmitter subscribe(@PathVariable("num") int num) {
		
        // Authentication을 UserDto로 업캐스팅
		//AlarmSenderDto alarmSenderDto = ClassUtils.getDefaultClassLoader(authentication.getClass(), AlarmSenderDto.class).orElseThrow(() -> new ApplicationException(ErrorCode.INTERNAL_SERVER_ERROR, "Casting to UserDto class failed"));
        
        // 서비스를 통해 생성된 SseEmitter를 반환
        return service.connectNotification(num);
    }
}
