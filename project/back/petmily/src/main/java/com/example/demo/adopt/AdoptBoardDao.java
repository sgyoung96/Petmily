package com.example.demo.adopt;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.member.Member;

@Repository
public interface AdoptBoardDao extends JpaRepository<Adoptboard, Integer> {
    ArrayList<Adoptboard> findById(Member id);
    ArrayList<Adoptboard> findByTitle(String title);

    @Transactional
    @Query(value = "SELECT COUNT(*) FROM Adoptboard")
    int countByAll();
}