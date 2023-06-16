package com.example.demo.diaryboard;

import java.io.File;
import java.io.IOException;
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

import com.example.demo.diarycomment.DiarycommentDto;

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
	
	//글추가
	@PostMapping("")
	public Map addDiary(DiaryboardDto dto) {
		boolean flag = true;//'flag'라는 boolean 변수를 'true'로 초기화
		try {//에외 발생 가능성 있으므로 try블록 안에서 실행
			int num = service.save(dto);//service.save를 호출하여 dto매개변수로 저장한 후 반환된 정수값을 num변수에 할당
			File dir = new File(path + "diaryboard/" + num);//디렉토리 경로
			dir.mkdir();//mkdir()메서드를 호출하여 디렉토리 생성
			MultipartFile[] f = dto.getF();//dto객체의 getF()를 호출하여 f생성
			String[] imgs = new String[2];//imgs를 초기화
			for (int i = 0; i < f.length; i++) {
				MultipartFile x = f[i];//x라는 새 MultipartFile객체 생성
				String fname = x.getOriginalFilename();//fname변수에 x의 원본 파일이름이 저장
				if (fname != null && !fname.equals("")) {
					String newpath = path + "diaryboard/" + num + "/" + fname; //새 파일 경로인 newpath가 구성됨
					File newfile = new File(newpath);//newpath를 사용하여 새File객체인 newfile이 생성됨
					System.out.println(newpath);
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
			service.save(dto);// 수정
		} catch (Exception e) {
			flag = false;
		}
		Map map = new HashMap();
		map.put("flag", flag);
		map.put("dto", dto);
		return map;
}
	
	@GetMapping("/{id}")
	public Map getById(@PathVariable("id")String id) {
		ArrayList<DiaryboardDto>list = service.getById(id);
		Map map = new HashMap();
		map.put("list", list);
		return map;
	}
	
	@GetMapping("/{title}")
	public Map getByTitle(@PathVariable("title")String title) {
		ArrayList<DiaryboardDto>list = service.getByTitle(title);
		Map map = new HashMap();
		map.put("list", list);
		return map;
	}
	
	@PutMapping("")
	public Map edit(DiaryboardDto dto) {
		Map map = new HashMap();
		DiaryboardDto dto2 = null;
		boolean flag = true;
		try {
			int num = service.save(dto);
			dto2 = service.getByNum(num);
		}catch(Exception e) {
			flag = false;
		}
		map.put("flag", flag);
		map.put("dto", dto2);
		return map;
	}
	
	@DeleteMapping("/{num}")
	public Map delete(@PathVariable("num") int num) {
		Map map = new HashMap();
		DiaryboardDto dto2 = null;
		boolean flag = true;
		try {
			service.delDiaryboard(num);
		} catch (Exception e) {
			flag = false;
		}
		map.put("flag", flag);
		return map;
	}
}
