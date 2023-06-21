package com.example.demo.volboard;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.member.Member;

import jakarta.transaction.Transactional;

@Repository
public interface VolboardDao extends JpaRepository<Volboard, Integer> {
	ArrayList<Volboard> findByTitle(String title);
	ArrayList<Volboard> findByWriter(Member writer);
	
	@Transactional
	@Modifying
	@Query(value="update volboard set vol_number=vol_number-1 where num=:num", nativeQuery = true)
	void updateIspay(@Param("num") int num);
}
