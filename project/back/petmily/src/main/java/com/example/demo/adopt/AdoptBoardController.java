package com.example.demo.adopt;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * 1. 전체 목록 검색
 * 2. 디테일 조회
 * 3. 글 추가
 * 4. 글 수정
 * 5. 글 삭제
 * @author gayeong
 *
 */
@RestController
@CrossOrigin(origins= "*")
@RequestMapping("/adaopt")
public class AdoptBoardController {
	@Autowired
	private AdoptBoardService service;
	
	@Value("${spring.servelt.multipart.location}")
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
	 * 글 추가
	 * @param dto
	 * @return
	 */
	@PostMapping("")
	public Map add(AdoptBoardDto dto) {
		boolean flag = true;
		try {
			int num = service.add(dto);
			File dir = new File(path + num);
			dir.mkdir();
			
			MultipartFile[] multipartFiles = dto.getMultipartFile();
			String[] images = new String[4];
			
			for (int i = 0; i < multipartFiles.length; i++) {
				MultipartFile multipartFile = multipartFiles[i];
				String fileName = multipartFile.getOriginalFilename();
				if (fileName != null && !fileName.equals("")) {
					String newPath = path + num + "/" + fileName;
					File newFile = new File(newPath);
					System.out.println(newPath);
					
					try {
						multipartFile.transferTo(newFile); // 파일 업로드
						images[i] = newPath;
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
			
			dto.setPic1(images[0]);
			dto.setPic2(images[1]);
			
			int saveNum = service.add(dto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	/**
	 * 글 수정
	 * @param dto
	 * @return
	 */
	@PutMapping("")
	public Map edit(AdoptBoardDto dto) {
		AdoptBoardDto origin = service.getDetail(dto.getNum());
		origin.setNum(dto.getNum());
		origin.setId(dto.getId());
		origin.setTitle(dto.getTitle());
		origin.setContent(dto.getContent());
		origin.setW_date(dto.getW_date());
		origin.setAddress(dto.getAddress());
		origin.setPic1(dto.getPic1());
		origin.setPic2(dto.getPic2());
		
		int num = service.edit(origin);
		
		Map map = new HashMap();
		map.put("num", num);
		return map;
	}
	
	/**
	 * 글 삭제
	 * @param num
	 */
	@DeleteMapping("")
	public void remove(int num) {
		service.remove(num);
	}
}
