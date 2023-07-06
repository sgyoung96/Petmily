package com.example.demo.applyform;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.member.Member;

import org.springframework.transaction.annotation.Transactional;

@Repository
public interface ApplyformDao extends JpaRepository<Applyform, Integer> {
	Applyform findById(int num);
	ArrayList<Applyform> findAllById(Member id);
	ArrayList<Applyform> findByIscheck(int ischeck);
	
	@Transactional
	@Modifying
	@Query(value="update applyform set ischeck=1 where num=:num", nativeQuery=true)
	void updateApply(@Param("num") int num);
	
	@Transactional
	@Modifying
	@Query(value="update applyform set ischeck=2 where num=:num", nativeQuery=true)
	void updateRefuse(@Param("num") int num);
	
	@Transactional
    @Query(value = "SELECT COUNT(*) FROM Applyform WHERE id = :id")
    int countById(@Param("id") Member id);
	
}
