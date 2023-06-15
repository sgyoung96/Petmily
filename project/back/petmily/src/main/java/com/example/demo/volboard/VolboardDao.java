package com.example.demo.volboard;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.member.Member;

@Repository
public interface VolboardDao extends JpaRepository<Volboard, Integer> {
	ArrayList<Volboard> findByTitle(String title);
	ArrayList<Volboard> findByWriter(Member writer);
	
}
