package com.example.demo.diaryboard;

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

import com.example.demo.adopt.AdoptBoardDto;
import com.example.demo.volboard.VolboardDto;

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
	
	//좋아요 순으로 검색
	@GetMapping("/ol")
	public Map getAllByol() {
		ArrayList<DiaryboardDto> list = service.getAllByLikecnt();
		Map map = new HashMap();
		map.put("list", list);
		return map;
	}
	
	//글 번호로 검색 디테일페이지
	@GetMapping("/{num}")
	public Map getByNum(@PathVariable("num") int num) {
		System.out.println("num :: " +num);
		DiaryboardDto dto = service.getByNum(num);
		Map map = new HashMap();
		map.put("dto", dto);
		System.out.println("dto :: " +dto.toString());
		return map;
	}
	
	//글추가
	@PostMapping("")
	public Map add(DiaryboardDto dto) {
		boolean flag = true;
		int saveNum = 0;
		try {
			int num = service.save(dto);
			File dir = new File(path + "diaryboard/" + num); //c:/petmily/diaryboard/num
			dir.mkdir();
			MultipartFile[] f = dto.getF();
			String[] imgs = new String[2];
			for(int i = 0; i < f.length; i++) {
				MultipartFile x = f[i];
				String fname = x.getOriginalFilename();
				if(fname != null && !fname.equals("")) {
					String newpath = path + "diaryboard/" + num + "/" + fname;
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
		DiaryboardDto dto2 = service.getByNum(saveNum);
		Map map = new HashMap();
		map.put("flag", flag);
		map.put("dto", dto2);
		return map;
	}
	
	@PutMapping("")
	public Map edit(DiaryboardDto dto) {
		int saveNum = 0;
		Map map = new HashMap();
		DiaryboardDto dto2 = service.getByNum(dto.getNum());
		System.out.println("#################################"+dto.getF()[0]+"#############################");
		boolean flag = true;
		if(dto.getF()[0] == null && dto.getF()[1] == null) {
			dto2.setTitle(dto.getTitle());
			dto2.setContent(dto.getContent());
			saveNum = service.save(dto2);
		}else {
		try {
			System.out.println("#@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
			dto2.setTitle(dto.getTitle());
			dto2.setContent(dto.getContent());
			File delf1 = new File(dto2.getPic1());
			delf1.delete();
			File delf2 = new File(dto2.getPic2());
			delf2.delete();
			int num = service.save(dto2);
			File dir = new File(path + "dirayboard/" + num);
			MultipartFile[] f = dto.getF();
			String[] imgs = new String[2];
			for(int i=0; i < f.length; i++) {
				MultipartFile x = f[i];
				String fname = x.getOriginalFilename();
				if(fname != null && !fname.equals("")) {
					String newpath = path + "diaryboard/" + num + "/" + fname;
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
			dto2.setPic1(imgs[0]);
			dto2.setPic2(imgs[1]);
			dto2.setNum(num);
			saveNum = service.save(dto2);
		}catch(Exception e) {
			flag = false;
		}
		}
		DiaryboardDto dto3 = service.getByNum(saveNum);
		map.put("flag", flag);
		map.put("dto", dto3);
		return map;
	}
	
	
	@DeleteMapping("/{num}")
	public Map delBoard(@PathVariable("num") int num) {
		boolean flag = true;
		Map map = new HashMap();
		DiaryboardDto dto = service.getByNum(num);
		File delf1 = new File(dto.getPic1());
		File delf2 = new File(dto.getPic2());
		File dir = new File(path + "diaryboard/" + num);
		try {
			if(dto.getPic1() != null && dto.getPic2() != null) {
				delf1.delete();
				delf2.delete();
				dir.delete();
				service.delDiaryboard(num);
			}else {
				service.delDiaryboard(num);
			}
		}catch(Exception e){
			e.printStackTrace();
			flag = false;
		}
		map.put("flag", flag);
		return map;
	}
	
	@GetMapping("/imgs/{num}/{idx}") // imgs/상품번호/이미지번호 /imgs/253/2
	public ResponseEntity<byte[]> read_img(@PathVariable("num") int num, @PathVariable("idx") int idx) {
		String fname = "";
		DiaryboardDto dto = service.getByNum(num);// 상품 번호로 검색
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
	
	 // id로 검색
    @GetMapping("/id/{id}")
    public Map getById(@PathVariable("id") String id) {
        Map map = new HashMap();
    	ArrayList<DiaryboardDto> dto = null;
    	boolean flag = true;
		try {
			dto = service.getById(id);
		} catch (Exception e) {
			flag = false;
		}
		map.put("flag", flag);
		map.put("dto", dto);
		return map;
	}

    // 제목으로 검색
    @GetMapping("/getByTitle/{title}")
    public ResponseEntity<ArrayList<DiaryboardDto>> getByTitle(@PathVariable String title) {
        ArrayList<DiaryboardDto> list = service.getByTitle(title);
        return ResponseEntity.ok(list);
    }
    
    //좋아요 수 올림
    @GetMapping("/likeup/{num}")
    public Map likeup(@PathVariable int num) {
    	Map map = new HashMap<>();
    	boolean flag = true;
    	try {
    		service.uplike(num);
    	} catch(Exception e) {
    		flag = false;
    	}
    	map.put("flag", flag);
    	return map;
    }
	
    //좋아요 수 내림
    @GetMapping("/likedown/{num}")
    public Map likedown(@PathVariable int num) {
    	Map map = new HashMap<>();
    	boolean flag = true;
    	try {
    		service.downlike(num);
    	} catch(Exception e) {
    		flag = false;
    	}
    	map.put("flag", flag);
    	return map;
    }
    
    @GetMapping("/count")
	public Map getAllCount() {
		boolean flag = true;
		Map map = new HashMap<>();
		int count = 0;
		try {
			count = service.getCount();
		} catch(Exception e) {
			flag = false;
		}
		map.put("flag", flag);
		map.put("count", count);
		return map;
	}
}
