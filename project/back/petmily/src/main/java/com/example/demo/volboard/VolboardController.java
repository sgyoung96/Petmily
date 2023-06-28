package com.example.demo.volboard;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
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
	
	//봉사모집게시판 디테일 페이지
	@GetMapping("/{num}")
	public Map getByNum(@PathVariable("num") int num) {
		VolboardDto dto = service.getById(num);
		Map map = new HashMap();
		map.put("dto", dto);
		return map;
	}
	
	@PostMapping("")
	public Map add(VolboardDto dto) {
		boolean flag = true;
		int saveNum = 0;
		try {
			int num = service.save(dto);
			File dir = new File(path + "volboard/" + num); //c:/petmily/volboard/num
			dir.mkdir();
			MultipartFile[] f = dto.getF();
			String[] imgs = new String[2];
			for(int i = 0; i < f.length; i++) {
				MultipartFile x = f[i];
				String fname = x.getOriginalFilename();
				if(fname != null && !fname.equals("")) {
					String newpath = path + "volboard/" + num + "/" + fname;
					File newfile = new File(newpath);
					try {
					x.transferTo(newfile);
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
			saveNum = service.save(dto);// 수정
		}catch (Exception e) {
			flag = false;
		}
		VolboardDto dto2 = service.getById(saveNum);
		Map map = new HashMap();
		map.put("flag", flag);
		map.put("dto", dto2);
		return map;
	}
	
	@GetMapping("/imgs/{num}/{idx}")
	public ResponseEntity<byte[]> read_img(@PathVariable("num") int num, @PathVariable("idx") int idx) {
		String fname = "";
		VolboardDto dto = service.getById(num);
		switch (idx) {
		case 1:
			fname = dto.getPic1();
			break;
		case 2:
			fname = dto.getPic2();
			break;
		default:
			return null;
		}
		System.out.println(fname);
		// 응답 객체를 생성해서 반환. 응답 객체는 헤더와 바디. 헤더:목적지주소, 나의주소, 마임타입, 크기...
		// 바디. 전송할 데이터
		File f = new File(fname);
		HttpHeaders header = new HttpHeaders(); // HttpHeaders 객체 생성
		ResponseEntity<byte[]> result = null; // 선언
		try {
			header.add("Content-Type", Files.probeContentType(f.toPath()));// 응답 데이터의 종류를 설정
			// 응답 객체 생성
			result = new ResponseEntity<byte[]>(FileCopyUtils.copyToByteArray(f), header, HttpStatus.OK);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	
	//제목으로 검색
	@GetMapping("/title/{title}")
	public Map getByTitle(@PathVariable("title") String title) {
		ArrayList<VolboardDto> list = service.getByTitle(title);
		Map map = new HashMap();
		map.put("list", list);
		return map;
	}
	
	//봉사장소로 검색
	@GetMapping("/address/{address}")
	public Map getByWriter(@PathVariable("address") String address) {
		ArrayList<VolboardDto> list = service.getByAddress(address);
		Map map = new HashMap();
		map.put("list", list);
		return map;
	}
	
	//봉사게시판 삭제
	@DeleteMapping("/{num}")
	public Map delBoard(@PathVariable("num") int num) {
		boolean flag = true;
		Map map = new HashMap();
		try {
			service.delBoard(num);
		}catch(Exception e){
			e.printStackTrace();
			flag = false;
		}
		map.put("flag", flag);
		return map;
	}
	
}
