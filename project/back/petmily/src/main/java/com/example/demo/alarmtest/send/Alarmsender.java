package com.example.demo.alarmtest.send;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * 알람 기능 사용하기 위해 임시로 만든 테이블
 * 추후 코멘트, 쪽지 알림의 역할을 할 예정임
 * @author gayeong
 *
 */
@Entity
@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Alarmsender {
	
	@Id
	@SequenceGenerator(name="seq_alarm_sender", sequenceName="seq_alarm_sender", allocationSize=1)//시퀀스 생성. sequenceName:시퀀스 이름
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seq_alarm_sender")//값 자동생성설정
	private int num;		// 고유 번호 (PK)
	private int clicked;
}

/*
	
CREATE OR REPLACE TRIGGER TR_MONITOR
AFTER INSERT ON ALARMSENDER
BEGIN 
	INSERT INTO MONITOR (NUM, CONTENT) VALUES (SEQ_MONITOR_NUM.NEXTVAL, '수정이력삽입');
END;
 
 
*/
