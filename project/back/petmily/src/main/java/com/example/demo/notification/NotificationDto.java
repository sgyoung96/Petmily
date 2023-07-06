package com.example.demo.notification;

import java.util.Date;

import org.springframework.web.multipart.MultipartFile;

import com.example.demo.member.Member;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class NotificationDto {

	private int num;				
	private Member reciever;	
	private String header;		
	private String content;		
	private int row_num;		
	private String title;		
	private String writer;		
	private String commenter;	
	private String notify_type;	
	private String is_clicked;
	private Date tr_date;		
}
