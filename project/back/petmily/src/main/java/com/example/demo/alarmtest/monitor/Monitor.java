package com.example.demo.alarmtest.monitor;

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
 * DB 동작 일어났을 시, 알람 이력 테이블에 저장하는 아이
 * @author gayeong
 *
 */
@Entity
@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Monitor {
	
	@Id
	@SequenceGenerator(name="seq_monitor_num", sequenceName="seq_monitor_num", allocationSize=1)//시퀀스 생성. sequenceName:시퀀스 이름
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seq_monitor_num")//값 자동생성설정
	private int num;		// 개인 분양 게시판 고유 번호 (PK)
	
	private String content; // 아무 문구
}