package com.example.demo.adoptliketable;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import com.example.demo.adopt.Adoptboard;
import com.example.demo.member.Member;

@Repository
public interface AdoptliketableDao extends JpaRepository<Adoptliketable, Integer> {
	Adoptliketable findByIdAndNum(Member id, Adoptboard num);
	void deleteByIdAndNum(Member id, Adoptboard num);
}
