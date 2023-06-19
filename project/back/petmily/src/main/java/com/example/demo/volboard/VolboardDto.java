package com.example.demo.volboard;

import java.util.Date;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.member.Member;

import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
public class VolboardDto {
	
	private int num;
	private Member writer;
	private String title;
	private String content;
	private Date vol_date;
	private int vol_number;
	private Date w_date;
	private String address;
	private String pic1;
	private String pic2;
	private int count;
	
	private MultipartFile[] f = new MultipartFile[2];
}
