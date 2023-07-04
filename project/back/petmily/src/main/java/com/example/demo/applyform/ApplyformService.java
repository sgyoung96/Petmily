package com.example.demo.applyform;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.member.Member;

@Service
public class ApplyformService {
	@Autowired
	private ApplyformDao dao;
	
	//추가, 수정
	public ApplyformDto save(ApplyformDto dto) {
		Applyform a = dao.save(new Applyform(dto.getNum(), dto.getId(), dto.getWdate(), dto.getAgreement(), 
				dto.getAnother(), dto.getReason(), dto.getFeeding(), dto.getIscheck(), dto.getPetCd()));
		return new ApplyformDto(a.getNum(), a.getId(), a.getWdate(), a.getAgreement(), a.getAnother(),
				a.getReason(), a.getFeeding(),a.getIscheck(), a.getPetCd());
	}
	
	//전체 검색
	public ArrayList<ApplyformDto> getAll(){
		ArrayList<Applyform> list = (ArrayList<Applyform>) dao.findAll();
		ArrayList<ApplyformDto> list2 = new ArrayList<ApplyformDto>();
		for (Applyform a : list) {
			list2.add(new ApplyformDto(a.getNum(), a.getId(), a.getWdate(), a.getAgreement(),
					a.getAnother(), a.getReason(), a.getFeeding(), a.getIscheck(), a.getPetCd()));
		}
		return list2;
	}
	
	//Id로 검색
		public ArrayList<ApplyformDto> findById(int num){
			ArrayList<Applyform> list = (ArrayList<Applyform>) dao.findById(num);
			ArrayList<ApplyformDto> list2 = new ArrayList<ApplyformDto>();
			for (Applyform a : list) {
				list2.add(new ApplyformDto(a.getNum(), a.getId(), a.getWdate(), a.getAgreement(),
						a.getAnother(), a.getReason(), a.getFeeding(), a.getIscheck(), a.getPetCd()));
			}
			return list2;
		}
	
	//Id로 검색
	public ArrayList<ApplyformDto> findByMemberId(String id){
		Member m = new Member("", id, "", "", "", null, "", "", "",null);
		ArrayList<Applyform> list = (ArrayList<Applyform>) dao.findAllById(m);
		ArrayList<ApplyformDto> list2 = new ArrayList<ApplyformDto>();
		for (Applyform a : list) {
			list2.add(new ApplyformDto(a.getNum(), a.getId(), a.getWdate(), a.getAgreement(),
					a.getAnother(), a.getReason(), a.getFeeding(), a.getIscheck(), a.getPetCd()));
		}
		return list2;
	}
	
	//check로 검색
	public ArrayList<ApplyformDto> findByCheck(int check){
		ArrayList<Applyform> list = (ArrayList<Applyform>) dao.findByIscheck(check);
		ArrayList<ApplyformDto> list2 = new ArrayList<ApplyformDto>();
		for (Applyform a : list) {
			list2.add(new ApplyformDto(a.getNum(), a.getId(), a.getWdate(), a.getAgreement(),
					a.getAnother(), a.getReason(), a.getFeeding(), a.getIscheck(), a.getPetCd()));
		}
		return list2;
	}
	
	//승인
	public void apply(int num) {
		dao.updateApply(num);
	}
	public void refuse(int num) {
		dao.updateRefuse(num);
	}
	//삭제 -> 거부
	public void delApplyform(int num) {
		dao.deleteById(num);
	}
	
}
