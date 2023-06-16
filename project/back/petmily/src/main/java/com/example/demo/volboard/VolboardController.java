package com.example.demo.volboard;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/volboard")
public class VolboardController {
	@Autowired
	private VolboardService service;
	
	@Value("${spring.servlet.multipart.location}")
	private String path;
	
	//봉사모집게시판 전체리스트 검색
	@GetMapping("")
	public Map getAll() {
		ArrayList<VolboardDto> list = service.getAll();
		Map map = new HashMap();
		map.put("list", list);
		return map;
	}
	
	@PostMapping("")
	public Map add(VolboardDto dto) {
		boolean flag = true;
		try {
			int num = service.save(dto);
			File dir = new File(path + "volboard/" + num); //c:/petmily/volboard/num
			dir.mkdir();
			MultipartFile f1 = dto.getF1();
			MultipartFile f2 = dto.getF2();
			String img1="";
			String img2="";
			String fname1 = f1.getOriginalFilename();
			String fname2 = f2.getOriginalFilename();
			if(fname1 != null && !fname1.equals("")) {
				String newpath = path + "volboard/" + num + "/" + fname1;
				File newfile = new File(newpath);
				try {
				f1.transferTo(newfile);
				img1 = newpath;
				} catch (IllegalStateException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(fname2 != null && !fname2.equals("")) {
				String newpath = path + "volboard/" + num + "/" + fname2;
				File newfile = new File(newpath);
				try {
				f1.transferTo(newfile);
				img2 = newpath;
				} catch (IllegalStateException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			dto.setPic1(img1);
			dto.setPic2(img2);
			dto.setNum(num);
			int saveNum = service.save(dto);// 수정
		}catch (Exception e) {
			flag = false;
		}
		Map map = new HashMap();
		map.put("flag", flag);
		map.put("dto", dto);
		return map;
	}
	
	//제목으로 검색
	@GetMapping("/title/{title}")
	public Map getByTitle(@PathVariable("title") String title) {
		ArrayList<VolboardDto> list = service.getByTitle(title);
		Map map = new HashMap();
		map.put("list", list);
		return map;
	}
	
	//작성자로 검색
	@GetMapping("/writer/{writer}")
	public Map getByWriter(@PathVariable("writer") String writer) {
		ArrayList<VolboardDto> list = service.getByWriter(writer);
		Map map = new HashMap();
		map.put("list", list);
		return map;
	}
	
}
