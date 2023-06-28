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

import com.example.demo.member.Member;
import com.example.demo.member.MemberDto;

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
	public Map addDiary(DiaryboardDto dto) {
		System.out.println("TEST :: " +dto.toString());
		boolean flag = true;//'flag'라는 boolean 변수를 'true'로 초기화
		try {//에외 발생 가능성 있으므로 try블록 안에서 실행
			int saveNum = service.save(dto);//service.save를 호출하여 dto매개변수로 저장한 후 반환된 정수값을 num변수에 할당(이미지폴더이름)
			System.out.println("dtonumnumnumnum :: " +dto.getNum());
			System.out.println("numnumnumnum :: " +saveNum);
			File dir = new File(path + "diaryboard/" + saveNum);//디렉토리 경로
			dir.mkdir();//mkdir()메서드를 호출하여 디렉토리 생성
			MultipartFile[] f = dto.getF();//dto객체의 getF()를 호출하여 f생성
			String[] imgs = new String[2];//imgs를 초기화
			for (int i = 0; i < f.length; i++) {
				MultipartFile x = f[i];//x라는 새 MultipartFile객체 생성
				String fname = x.getOriginalFilename();//fname변수에 x의 원본 파일이름이 저장
				if (fname != null && !fname.equals("")) {
					String newpath = path + "diaryboard/" + saveNum + "/" + fname; //새 파일 경로인 newpath가 구성됨
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
			dto.setNum(saveNum);
			service.save(dto);// 수정
		} catch (Exception e) {
			flag = false;
		}
		Map map = new HashMap();
		map.put("flag", flag);
		map.put("dto", dto);
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
}
