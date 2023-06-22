package com.example.demo.diaryboard;

import java.util.ArrayList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.member.Member;
@Repository
public interface DiaryboardDao extends JpaRepository<Diaryboard, Integer> {
	ArrayList<Diaryboard> findByIdContaining(Member id);
	ArrayList<Diaryboard> findByTitleContaining(String title);
	ArrayList<Diaryboard> findAllByOrderByNumDesc();
}
