package com.example.demo.volboard;

import java.util.ArrayList;
import java.util.List;

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
	List<Volboard> findByAddressContaining(String address);
	ArrayList<Volboard> findAllByOrderByCnt();
	
	@Transactional
	@Modifying
	@Query(value="update volboard set count=count+1 where num=:num", nativeQuery = true)
	void updateCount(@Param("num") int num);
	
	@Transactional
	@Modifying
	@Query(value="update volboard set count=count-1 where num=:num", nativeQuery = true)
	void updateCount2(@Param("num") int num);
	
	@Transactional
	@Modifying
	@Query(value="update volboard set cnt=cnt+1 where num=:num", nativeQuery = true)
	void updateCnt(@Param("num") int num);
	
	@Transactional
	@Query(value = "SELECT COUNT(*) FROM Volboard")
	int countByAll();
	
	// id를 받아와서 그 id의 작성한게시판 개수 출력
    @Transactional
    @Query(value = "SELECT COUNT(*) FROM Volboard WHERE writer = :writer")
    int countById(@Param("writer") Member writer);
}
