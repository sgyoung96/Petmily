package com.example.demo.diarycomment;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.diaryboard.Diaryboard;
@Repository
public interface DiarycommentDao extends JpaRepository<Diarycomment, Integer> {
	ArrayList<Diarycomment> findByNum(Diaryboard num);
}
