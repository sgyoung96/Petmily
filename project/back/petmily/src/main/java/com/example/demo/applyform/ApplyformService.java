package com.example.demo.applyform;

import java.util.ArrayList;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.member.Member;

@Service
public class ApplyformService {
	@Autowired
	private ApplyformDao dao;

	// 추가, 수정
	public ApplyformDto save(ApplyformDto dto) {
		Applyform a = dao.save(new Applyform(dto.getNum(), dto.getId(), dto.getWdate(), dto.getAgreement(),
				dto.getAnother(), dto.getReason(), dto.getFeeding(), dto.getIscheck(), dto.getKindCd(), dto.getSexCd(),
				dto.getAge(), dto.getColorCd(), dto.getNeuterYn(), dto.getCareNm(), dto.getCareAddr(),
				dto.getPopfile()));
		return new ApplyformDto(a.getNum(), a.getId(), a.getWdate(), a.getAgreement(), a.getAnother(), a.getReason(),
				a.getFeeding(), a.getIscheck(), a.getKindCd(), a.getSexCd(), a.getAge(), a.getColorCd(),
				a.getNeuterYn(), a.getCareNm(), a.getCareAddr(), a.getPopfile());
	}

	// 전체 검색
	public ArrayList<ApplyformDto> getAll() {
		ArrayList<Applyform> list = (ArrayList<Applyform>) dao.findAll();
		ArrayList<ApplyformDto> list2 = new ArrayList<ApplyformDto>();
		for (Applyform a : list) {
			list2.add(new ApplyformDto(a.getNum(), a.getId(), a.getWdate(), a.getAgreement(), a.getAnother(),
					a.getReason(), a.getFeeding(), a.getIscheck(), a.getKindCd(), a.getSexCd(), a.getAge(),
					a.getColorCd(), a.getNeuterYn(), a.getCareNm(), a.getCareAddr(), a.getPopfile()));
		}
		return list2;
	}
	// 내림차순
	public ArrayList<ApplyformDto> getAllbyischeck() {
		ArrayList<Applyform> list = (ArrayList<Applyform>) dao.findAllByOrderByIscheckAsc();
		ArrayList<ApplyformDto> list2 = new ArrayList<ApplyformDto>();
		for (Applyform a : list) {
			list2.add(new ApplyformDto(a.getNum(), a.getId(), a.getWdate(), a.getAgreement(), a.getAnother(),
					a.getReason(), a.getFeeding(), a.getIscheck(), a.getKindCd(), a.getSexCd(), a.getAge(),
					a.getColorCd(), a.getNeuterYn(), a.getCareNm(), a.getCareAddr(), a.getPopfile()));
		}
		return list2;
	}

	// Id로 검색
	public ApplyformDto getById(int num) {
		Applyform a = dao.findById(num);

		return new ApplyformDto(a.getNum(), a.getId(), a.getWdate(), a.getAgreement(), a.getAnother(),
				a.getReason(), a.getFeeding(), a.getIscheck(), a.getKindCd(), a.getSexCd(), a.getAge(),
				a.getColorCd(), a.getNeuterYn(), a.getCareNm(), a.getCareAddr(), a.getPopfile());
	}

	// 맴버Id로 검색
	public ArrayList<ApplyformDto> findByMemberId(String id) {
		Member m = new Member(id, "", "", "", "", null, "", "", "", null);
		ArrayList<Applyform> list = (ArrayList<Applyform>) dao.findAllById(m);
		ArrayList<ApplyformDto> list2 = new ArrayList<ApplyformDto>();
		for (Applyform a : list) {
			list2.add(new ApplyformDto(a.getNum(), a.getId(), a.getWdate(), a.getAgreement(), a.getAnother(),
					a.getReason(), a.getFeeding(), a.getIscheck(), a.getKindCd(), a.getSexCd(), a.getAge(),
					a.getColorCd(), a.getNeuterYn(), a.getCareNm(), a.getCareAddr(), a.getPopfile()));
		}
		return list2;
	}
	
	// 맴버 ID와 check로 검색
	public ArrayList<ApplyformDto> searchByMemberIdAndCheck(String id, int check) {
	    ArrayList<ApplyformDto> list1 = findByMemberId(id);
	    ArrayList<ApplyformDto> list2 = findByCheck(check);
	    ArrayList<ApplyformDto> resultList = new ArrayList<>();

	    for (ApplyformDto dto : list1) {
	        if (list2.contains(dto)) {
	            resultList.add(dto);
	        }
	    }

	    return resultList;
	}
	// check로 검색
	public ArrayList<ApplyformDto> findByCheck(int check) {
		ArrayList<Applyform> list = (ArrayList<Applyform>) dao.findByIscheck(check);
		ArrayList<ApplyformDto> list2 = new ArrayList<ApplyformDto>();
		for (Applyform a : list) {
			list2.add(new ApplyformDto(a.getNum(), a.getId(), a.getWdate(), a.getAgreement(), a.getAnother(),
					a.getReason(), a.getFeeding(), a.getIscheck(), a.getKindCd(), a.getSexCd(), a.getAge(),
					a.getColorCd(), a.getNeuterYn(), a.getCareNm(), a.getCareAddr(), a.getPopfile()));
		}
		return list2;
	}

	// 승인
	public void apply(int num) {
		dao.updateApply(num);
	}

	// 삭제 -> 거부
	//거부
	public void refuse(int num) {
		dao.updateRefuse(num);
	}
	//삭제
	public void delApplyform(int num) {
		dao.deleteById(num);
	}

	// 신청한 개수 출력
	public int printPerson(String id) {
		Member m = new Member(id, "", "", "", "", null, "", "", "", null);
		return dao.countById(m);
	}

}
