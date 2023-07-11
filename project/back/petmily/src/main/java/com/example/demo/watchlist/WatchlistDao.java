package com.example.demo.watchlist;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.member.Member;
import com.example.demo.volboard.Volboard;

@Repository
public interface WatchlistDao extends JpaRepository<Watchlist, Integer> {
	Watchlist findByIdAndNum(Member id, Volboard num);
	ArrayList<Watchlist> findById(Member id);
	
	// id를 받아와서 그 id의 관심목록 개수 출력
    @Transactional
    @Query(value = "SELECT COUNT(*) FROM Watchlist WHERE id = :id")
    int countById(@Param("id") Member id);
}
