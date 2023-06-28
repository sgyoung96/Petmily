package com.example.demo.watchlist;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.member.Member;
import com.example.demo.volboard.Volboard;

@Repository
public interface WatchlistDao extends JpaRepository<Watchlist, Integer> {
	Watchlist findByIdAndNum(Member id, Volboard num);
}
