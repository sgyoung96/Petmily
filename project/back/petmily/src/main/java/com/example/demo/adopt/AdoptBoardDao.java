package com.example.demo.adopt;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdoptBoardDao extends JpaRepository<Adoptboard, Integer>{

	ArrayList<Adoptboard> getAll();			// 전체 조회
	Adoptboard findByNum(int num);			// 디테일 조회
	Adoptboard save(Adoptboard adoptBoard);	// 글 작성 및 수정
	void delete(int num);				// 글 삭제
}
