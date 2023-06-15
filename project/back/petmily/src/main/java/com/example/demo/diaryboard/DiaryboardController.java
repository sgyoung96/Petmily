package com.example.demo.diaryboard;

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
@CrossOrigin(origins="*")
@RequestMapping("/dboard")
public class DiaryboardController {
	@Autowired
	private DiaryboardService service;
	
	@Value("${spring.servlet.multipart.location}")
	private String path; // C:/petmily/
	
	//전체목록 검색
	@GetMapping("")
	public Map getAll() {
		ArrayList<DiaryboardDto> list = service.getAll();
		Map map = new HashMap();
		map.put("list", list);
		return map;
	}
	
	@PostMapping("")
	public void addDiary(DiaryboardDto dto) {
		boolean flag = true;
		try {
			int num = service.save(dto);// 추가. 추가될때 번호 자동 생성. 그 번호를 반환
			File dir = new File(path + num);
			dir.mkdir();// C:/shop/상품번호 인 디렉토리 생성 => 상품 이미지 폴더
			MultipartFile[] f = dto.getF();
			String[] imgs = new String[2];
			for (int i = 0; i < f.length; i++) {
				MultipartFile x = f[i];
				String fname = x.getOriginalFilename();
				if (fname != null && !fname.equals("")) {
					// String fname = x.getOriginalFilename();//원본파일명
					String newpath = path + num + "/" + fname;
					File newfile = new File(newpath);// 복사할 새 파일 생성. c:/shop/번호/원본파일명
					System.out.println(newpath);
					try {
						x.transferTo(newfile);// 파일 업로드
						imgs[i] = newpath;
					} catch (IllegalStateException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
			dto.setPic1(imgs[0]);
			dto.setPic2(imgs[1]);
			dto.setNum(num);
			service.save(dto);// 수정
		} catch (Exception e) {
			flag = false;
		}
}
	
	@GetMapping("/id/{id}")
	public Map getById(@PathVariable("id")String id) {
		ArrayList<DiaryboardDto>list = service.getById(id);
		Map map = new HashMap();
		map.put("list", list);
		return map;
	}
	
	@GetMapping("/title/{title}")
	public Map getByTitle(@PathVariable("title")String title) {
		ArrayList<DiaryboardDto>list = service.getByTitle(title);
		Map map = new HashMap();
		map.put("list", list);
		return map;
	}
}
