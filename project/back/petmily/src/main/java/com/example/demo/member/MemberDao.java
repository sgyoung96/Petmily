package com.example.demo.member;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface MemberDao extends JpaRepository<Member, String> {
	ArrayList<Member> findByEmail(String email);
	
	
}
