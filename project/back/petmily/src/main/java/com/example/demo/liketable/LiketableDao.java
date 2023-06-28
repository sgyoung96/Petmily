package com.example.demo.liketable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.diaryboard.Diaryboard;
import com.example.demo.member.Member;

@Repository
public interface LiketableDao extends JpaRepository<Liketable, Integer> {
	Liketable findByIdAndNum(Member id, Diaryboard num);
}
