package com.example.demo.adopt;

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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.diaryboard.DiaryboardDto;
import com.example.demo.volboard.VolboardDto;

/**
 * 1. 전체 목록 검색
 * 2. 디테일 조회
 * 3. 글 추가
 * 5. 글 삭제
 * @author gayeong
 *
 */
@RestController
@CrossOrigin(origins= "*")
@RequestMapping("/adopt")
public class AdoptBoardController {
	@Autowired
	private AdoptBoardService service;
	
	@Value("${spring.servlet.multipart.location}")
	private String path;
	
	/**
	 * 전체 목록 검색
	 * @return
	 */
	@GetMapping("")
	public Map getAll() {
		ArrayList<AdoptBoardDto> list = service.getAll();
		Map map = new HashMap();
		map.put("list",  list);
		return map;
	}
	
	/**
	 * 디테일 조회
	 * @param num
	 * @return
	 */
	@GetMapping("/{num}")
	public Map getByNum(@PathVariable("num") int num) {
		AdoptBoardDto dto = service.getDetail(num);
		Map map = new HashMap();
		map.put("dto", dto);
		return map;
	}
	
	/**
	 * 글 작성 및 수정
	 * @param dto
	 * @return
	 */
	@PostMapping("")
	public Map add(AdoptBoardDto dto) {
		boolean flag = true;
		int saveNum = 0;
		try {
			int num = service.add(dto);
			File dir = new File(path + "adoptboard/" + num); //c:/petmily/adoptboard/num
			dir.mkdir();
			MultipartFile[] f = dto.getF();
			String[] imgs = new String[2];
			for(int i = 0; i < f.length; i++) {
				MultipartFile x = f[i];
				String fname = x.getOriginalFilename();
				if(fname != null && !fname.equals("")) {
					String newpath = path + "adoptboard/" + num + "/" + fname;
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
			saveNum = service.add(dto);// 수정
		}catch (Exception e) {
			flag = false;
		}
		AdoptBoardDto dto2 = service.getDetail(saveNum);
		Map map = new HashMap();
		map.put("flag", flag);
		map.put("dto", dto2);
		return map;
	}
	
	@GetMapping("/imgs/{num}/{idx}")
	public ResponseEntity<byte[]> read_img(@PathVariable("num") int num, @PathVariable("idx") int idx) {
		String fname = "";
		AdoptBoardDto dto = service.getDetail(num);
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
	
	@PutMapping("")
	public Map edit(AdoptBoardDto dto) {
		Map map = new HashMap();
		AdoptBoardDto dto3 = null;
		AdoptBoardDto dto2 = service.getDetail(dto.getNum());
		boolean flag = true;
		try {
			dto2.setContent(dto.getContent());
			int num = service.add(dto2);
			dto3 = service.getDetail(num);
		}catch(Exception e) {
			flag = false;
		}
		map.put("flag", flag);
		map.put("dto", dto3);
		return map;
	}
	
	/**
	 * 글 삭제
	 * @param num
	 */
	@DeleteMapping("/{num}")
	public Map delBoard(@PathVariable("num") int num) {
		boolean flag = true;
		Map map = new HashMap();
		AdoptBoardDto dto = service.getDetail(num);
		File delf1 = new File(dto.getPic1());
		File delf2 = new File(dto.getPic2());
		File dir = new File(path + "adoptboard/" + num);
		try {
			if(dto.getPic1() != null && dto.getPic2() != null) {
				delf1.delete();
				delf2.delete();
				dir.delete();
				service.remove(num);
			}else {
				service.remove(num);
			}
		}catch(Exception e){
			e.printStackTrace();
			flag = false;
		}
		map.put("flag", flag);
		return map;
	}
}
