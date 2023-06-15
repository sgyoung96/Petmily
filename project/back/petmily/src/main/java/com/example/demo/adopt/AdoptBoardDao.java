package com.example.demo.adopt;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdoptBoardDao extends JpaRepository<Adoptboard, Integer>{

	ArrayList<Adoptboard> getAll();			// 전체 조회
	Adoptboard getDetail(int num);			// 디테일 조회
	Adoptboard add(Adoptboard adoptBoard);	// 글 작성
	Adoptboard edit(Adoptboard adoptBoard);	// 글 수정
	void deleteByNum(int num);				// 글 삭제
}
