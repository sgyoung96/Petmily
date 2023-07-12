package com.example.demo.applyform;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.diarycomment.DiarycommentDto;

@RestController // rest api controller
@CrossOrigin(origins = "*") // 모든 ip로부터 요청 받기 허용
@RequestMapping("/Applyform")
public class ApplyformController {
	@Autowired
	private ApplyformService service;
	
	// 추가
	@PostMapping("")
	public Map add(ApplyformDto dto) {
		System.out.println("id : " + dto.getId() + "num : " + dto.getNum());
		ApplyformDto d = service.save(dto);
		Map map = new HashMap();
		map.put("dto", d);
		return map;
	}
	
	///수정
	@PutMapping("")
	public Map edit(ApplyformDto dto) {
		ApplyformDto a = service.getById(dto.getNum());
		a.setAnother(dto.getAnother());
		a.setFeeding(dto.getFeeding());
		a.setReason(dto.getReason());
		ApplyformDto dto2 = null;
		boolean flag = true;
		try {
			dto2 = service.save(a);
		} catch (Exception e) {
			flag = false;
		}
	
		Map map = new HashMap();
		map.put("flag", flag);
		map.put("dto", dto2);
		return map;
	}
	
	// 전체목록 검색
	@GetMapping("")
	public Map getAll() {
		ArrayList<ApplyformDto> list = service.getAll();
		Map map = new HashMap();
		map.put("list", list);
		return map;
	}
	
	@GetMapping("/getAllbyischeck")
	public Map getAllbyischeck() {
		ArrayList<ApplyformDto> list = service.getAllbyischeck();
		Map map = new HashMap();
		map.put("list", list);
		return map;
	}
	

	    @GetMapping("/getbynum/{num}")
	    public Map<String, Object> getbynum(@PathVariable("num") int num) {
	        ApplyformDto a = service.getById(num);
	        Map<String, Object> map = new HashMap<>();

	        map.put("num", a.getNum());
	        map.put("id", a.getId());
	        map.put("wdate", a.getWdate());
	        map.put("agreement", a.getAgreement());
	        map.put("another", a.getAnother());
	        map.put("reason", a.getReason());
	        map.put("feeding", a.getFeeding());
	        map.put("ischeck", a.getIscheck());
	        map.put("kindCd", a.getKindCd());
	        map.put("sexCd", a.getSexCd());
	        map.put("age", a.getAge());
	        map.put("colorCd", a.getColorCd());
	        map.put("neuterYn", a.getNeuterYn());
	        map.put("careNm", a.getCareNm());
	        map.put("careAddr", a.getCareAddr());
	        map.put("popfile", a.getPopfile());

	        return map;
	    }

	
	//맴버id로 검색
	@GetMapping("/getbyid/{id}")
	public Map getbyid(@PathVariable("id")String id) {
		ArrayList<ApplyformDto> list = service.findByMemberId(id);
		Map map = new HashMap();
		map.put("list", list);
		return map;
	}
	
	 
	 
	@GetMapping("/search/{id}/{check}")
	public Map searchByMemberIdAndCheck(@PathVariable("id") String id, @PathVariable("check") int check) {
	    ArrayList<ApplyformDto> resultList = service.searchByMemberIdAndCheck(id, check);
	    Map map = new HashMap<>();
	    map.put("list", resultList);
	    return map;
	}
	
		//check로 검색
		@GetMapping("/getbycheck/{check}")
		public Map getbycheck(@PathVariable("check") int check) {
			ArrayList<ApplyformDto> list = service.findByCheck(check);
			Map map = new HashMap();
			map.put("list", list);
			return map;
		}
	//승인
	@PatchMapping("/apply/{num}")
	public Map apply(@PathVariable("num") int num) {
		boolean flag = true;
		try {
			service.apply(num);
		} catch (Exception e) {
			flag = false;
		}
		Map map = new HashMap();
		map.put("flag", flag);
		return map;
	}	
	
	//거부
		@PatchMapping("/refuse/{num}")
		public Map refuse(@PathVariable("num") int num) {
			boolean flag = true;
			try {
				service.refuse(num);
			} catch (Exception e) {
				flag = false;
			}
			Map map = new HashMap();
			map.put("flag", flag);
			return map;
		}	
		
	//삭제
	@DeleteMapping("/{num}")
	public void delform(@PathVariable("num") int num) {
		service.delApplyform(num);
	}
	
	//작성한 게시판개수 출력
  	@GetMapping("/id/{id}")
  	public Map getWatch(@PathVariable("id") String id) {
  		Map map = new HashMap<>();
  		int dto = service.printPerson(id);
  		map.put("dto", dto);
  		return map;
  	}
	
}
