package com.example.demo.applyform;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.member.Member;

import jakarta.transaction.Transactional;
@Repository
public interface ApplyformDao extends JpaRepository<Applyform, Integer> {
	ArrayList<Applyform> findByMemberId(Member id);
	ArrayList<Applyform> findByCheck(int check);
	
	@Transactional
	@Modifying
	@Query(value="update applyform set check=1 where num=:num", nativeQuery=true)
	void updateApply(@Param("num") int num);
}
