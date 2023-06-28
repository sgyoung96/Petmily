package com.example.demo.member;

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
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.auth.JwtTokenProvider;



@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/members")
public class MemberController {
	@Autowired
	private MemberService service;
	
	@Autowired
	private JwtTokenProvider tokenprovider;// 멤버변수로 토큰 브로바이더 추가
	
	//application.properties 파일의 속성값 읽기
	@Value("${spring.servlet.multipart.location}") 
	private String path; 

//	// 가입
//	@PostMapping("")
//	public Map join(MemberDto dto) {
//		System.out.println(dto);
//		System.out.println("가입해야지");
//		MemberDto d = service.save(dto);
//		Map map = new HashMap();
//		map.put("dto", d);
//		return map;
//	}
	
	// multipart가입
	@PostMapping("")
	public Map join(@RequestParam(required=false) MultipartFile f, MemberDto dto) {
		System.out.println(dto);
		System.out.println("가입해야지");
		
		boolean flag = true;
		MemberDto d = service.save(dto);

		
		Map map = new HashMap();
		
		File dir = new File(path + "member/" + d.getId());
		dir.mkdir();

		f = dto.getF();
		String fname = f.getOriginalFilename();
		
		if (fname != null && !fname.equals("")) { // 업로드된 파일이 있으면
	
			String newpath = path + "member/" + d.getId() + "/" + fname;
			File newfile = new File(newpath); // 복사할 새 파일 생성. c:/img/shop/번호/원본파일명
			System.out.println(newpath);
			try {
				f.transferTo(newfile);//파일 업로드 
				dto.setProfile(newpath);
			} catch (IllegalStateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		service.save(dto);
		map.put("dto", d);
		return map;
	}
	
	//프로필 사진
	@GetMapping("/imgs/{id}")  //imgs/id/
	public ResponseEntity<byte[]> read_img(@PathVariable("id") String id) {
		String fname = "";
		MemberDto dto = service.getMember(id);
		fname = dto.getProfile();
		System.out.println(fname);
	
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
	
	
	//==================== token ======================
	
	
	// idcheck 검색
	// 검색
	@GetMapping("/{id}")
	public Map get(@PathVariable("id") String id, @RequestHeader(name = "token", required = false) String token) {
		System.out.println("idcheck");
		Map map = new HashMap();

		if (token != null) { // 로그인 후
			try {
				String id2 = tokenprovider.getUsernameFromToken(token);
				if (!id.equals(id2)) {
					map.put("dto", null);
					return map;
				}
			} catch (Exception e) {
				map.put("dto", null);
				return map;
			}
		}
		MemberDto d = service.getMember(id);
		map.put("dto", d);
		System.out.println(d);
		return map;
		}
	
	//email검색
	@GetMapping("/email/{email}")
	public Map getbyemail(@PathVariable("email") String email) {
		System.out.println("emailcheck");
		Map map = new HashMap();

	
		ArrayList<MemberDto> d = service.getByEmail(email);
		if(d==null) {
			map.put("dto", d);
			return map;
		}
		MemberDto m = d.get(0);
		map.put("dto", m);
		System.out.println("email : " + m);
		return map;
	}
	
	
	//로그인
	@PostMapping("/login")
	public Map login(String id, String pwd) {
		Map map = new HashMap();
		boolean flag = false;
		MemberDto dto = service.getMember(id);
		if (dto != null && pwd.equals(dto.getPwd())) {
			String token = tokenprovider.generateJwtToken(dto); // 토큰 생성
			flag = true;
			map.put("token", token);
			System.out.println("token : " + token);
		}
		map.put("dto",dto);
		map.put("flag", flag);
		return map;
	}
	
	//검색
	@PostMapping("/token")
	public Map getByToken(String token) {
		boolean flag = true;
		Map map = new HashMap();
		try {
			// 받은 토큰에서 로그인한 사람의 아이디, 타입 정보를 추출
			String id = tokenprovider.getUsernameFromToken(token);
			map.put("id", id);
		} catch (Exception e) {
			flag = false;
		}
		
		map.put("flag", flag);
		return map;
	}
	
	// 검색
	@GetMapping("")
	public Map getInfo(@RequestHeader("token") String token) { // @RequestHeader("token") : 요청 헤더 객체 받아오기
		boolean flag = true;
		Map map = new HashMap();
		try {
			String id = tokenprovider.getUsernameFromToken(token);
			map.put("id", id);
			
		} catch (Exception e) {
			flag = false;
		}
		map.put("flag", flag);
		return map;
		}
	
	//내정보 수정
	@PutMapping("")
	public Map edit(MemberDto dto) {
		MemberDto old = service.getMember(dto.getId());
		
		MultipartFile f = dto.getF();
		System.out.println("getF : " + dto.getF());
		String fname = f.getOriginalFilename();
		
		if (fname != null && !fname.equals("")) { // 업로드된 파일이 있으면
			//String fname = x.getOriginalFilename();//원본파일명
			String newpath = path + "member/" + old.getId() + "/" + fname;
			File newfile = new File(newpath); // 복사할 새 파일 생성. c:/img/shop/번호/원본파일명
			System.out.println(newpath);
			try {
				f.transferTo(newfile);//파일 업로드 
				dto.setProfile(newpath);
				String delf = old.getProfile();
				if(delf != null) {
					File delFile = new File(delf);
					delFile.delete();
				}
				old.setProfile(dto.getProfile());
			} catch (IllegalStateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		old.setEmail(dto.getEmail());
		old.setPwd(dto.getPwd());
		old.setPhone(dto.getPhone());
		
		
		MemberDto d = service.save(old);
		Map map = new HashMap();
		map.put("dto",d);
		return map;
	}
	

	//비밀번호 수정
	@PutMapping("/pwd")
	public Map changepwd(MemberDto dto) {
		System.out.println("비밀번호 찾기 수정");
		MemberDto old = service.getMember(dto.getId());
		
		old.setPwd(dto.getPwd());
		

		MemberDto d = service.save(old);
		Map map = new HashMap();
		map.put("dto",d);
		return map;
	}
	
	//탈퇴
	@DeleteMapping("/{id}")
	public Map del(@PathVariable("id") String id) {

		boolean flag = true;
		try {
			service.delMember(id);
		} catch (Exception e) {
			flag = false;
		}
		Map map = new HashMap();
		map.put("flag", flag);
		return map;
	}
	

}
