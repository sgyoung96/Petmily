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

/**
 * 1. 전체 목록 검색 2. 디테일 조회 3. 글 추가 5. 글 삭제
 * 
 * @author gayeong
 *
 */
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/adopt")
public class AdoptBoardController {
	@Autowired
	private AdoptBoardService service;

	@Value("${spring.servlet.multipart.location}")
	private String path;

	/**
	 * 전체 목록 검색
	 * 
	 * @return
	 */
	@GetMapping("")
	public Map getAll() {
		ArrayList<AdoptBoardDto> list = service.getAll();
		Map map = new HashMap();
		map.put("list", list);
		return map;
	}

	/**
	 * 좋아요 순으로 전체 목록 검색
	 * 
	 * @return
	 */
	@GetMapping("/ol")
	public Map getAllOl() {
		ArrayList<AdoptBoardDto> list = service.getAllOl();
		Map map = new HashMap();
		map.put("list", list);
		return map;
	}

	/**
	 * 디테일 조회
	 * 
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
	 * ID로 조회
	 * 
	 * @param id
	 * @return
	 */
	@GetMapping("/id2/{id}")
	public Map getById(@PathVariable("id") String id) {
		Map map = new HashMap();
		ArrayList<AdoptBoardDto> list = null;
		boolean flag = true;
		try {
			list = service.getByWriter(id);
		} catch (Exception e) {
			flag = false;
		}
		map.put("flag", flag);
		map.put("list", list);
		return map;
	}

	/**
	 * 글 작성 및 수정
	 * 
	 * @param dto
	 * @return
	 */
	@PostMapping("")
	public Map add(AdoptBoardDto dto) {
		boolean flag = true;
		int saveNum = 0;
		try {
			int num = service.add(dto);
			File dir = new File(path + "adoptboard/" + num); // c:/petmily/adoptboard/num
			dir.mkdir();
			MultipartFile[] f = dto.getF();
			String[] imgs = new String[2];
			for (int i = 0; i < f.length; i++) {
				MultipartFile x = f[i];
				String fname = x.getOriginalFilename();
				if (fname != null && !fname.equals("")) {
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
		} catch (Exception e) {
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
		int saveNum = 0;
		Map map = new HashMap();
		AdoptBoardDto dto2 = service.getDetail(dto.getNum());
		boolean flag = true;
		if (dto.getF()[0] == null && dto.getF()[1] == null) {
			dto2.setTitle(dto.getTitle());
			dto2.setContent(dto.getContent());
			saveNum = service.add(dto2);
		} else {
			try {
				dto2.setTitle(dto.getTitle());
				dto2.setContent(dto.getContent());
				File delf1 = new File(dto2.getPic1());
				delf1.delete();
				File delf2 = new File(dto2.getPic2());
				delf2.delete();
				int num = service.add(dto2);
				File dir = new File(path + "adoptboard/" + num);
				MultipartFile[] f = dto.getF();
				String[] imgs = new String[2];
				for (int i = 0; i < f.length; i++) {
					MultipartFile x = f[i];
					String fname = x.getOriginalFilename();
					if (fname != null && !fname.equals("")) {
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
				dto2.setPic1(imgs[0]);
				dto2.setPic2(imgs[1]);
				dto2.setNum(num);
				saveNum = service.add(dto2);
			} catch (Exception e) {
				flag = false;
			}
		}
		AdoptBoardDto dto3 = service.getDetail(saveNum);
		map.put("flag", flag);
		map.put("dto", dto3);
		return map;
	}

	/**
	 * 글 삭제
	 * 
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
			if (dto.getPic1() != null && dto.getPic2() != null) {
				delf1.delete();
				delf2.delete();
				dir.delete();
				service.remove(num);
			} else {
				service.remove(num);
			}
		} catch (Exception e) {
			e.printStackTrace();
			flag = false;
		}
		map.put("flag", flag);
		return map;
	}

	// 좋아요 수 올림
	@GetMapping("/likeup/{num}")
	public Map likeup(@PathVariable int num) {
		Map map = new HashMap<>();
		boolean flag = true;
		try {
			service.uplike(num);
		} catch (Exception e) {
			flag = false;
		}
		map.put("flag", flag);
		return map;
	}

	// 좋아요 수 내림
	@GetMapping("/likedown/{num}")
	public Map likedown(@PathVariable int num) {
		Map map = new HashMap<>();
		boolean flag = true;
		try {
			service.downlike(num);
		} catch (Exception e) {
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
		} catch (Exception e) {
			flag = false;
		}
		map.put("flag", flag);
		map.put("count", count);
		return map;
	}

	// 작성한 게시판개수 출력
	@GetMapping("/id/{id}")
	public Map getWatch(@PathVariable("id") String id) {
		Map map = new HashMap<>();
		int dto = service.printPerson(id);
		map.put("dto", dto);
		return map;
	}

	// 조회수 올림
	@GetMapping("/cnt/{num}")
	public Map upCnt(@PathVariable("num") int num) {
		boolean flag = true;
		Map map = new HashMap<>();
		try {
			service.upCnt2(num);
		} catch (Exception e) {
			flag = false;
		}
		map.put("flag", flag);
		return map;
	}
	
	// 분양완료, 분양취소
	@GetMapping("/ischeck/{num}")
	public Map updateIscheck(@PathVariable("num") int num) {
		boolean flag = true;
		Map map = new HashMap<>();
		try {
			AdoptBoardDto dto = service.getDetail(num);
			if(dto.getIscheck() == 0) {
				service.upIsCheck(num);
			}else {
				service.downIsCheck(num);
			}
		}catch (Exception e){
			flag = false;
		}
		map.put("flag", flag);
		return map;
	}
}
