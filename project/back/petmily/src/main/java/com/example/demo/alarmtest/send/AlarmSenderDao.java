package com.example.demo.alarmtest.send;

import java.util.ArrayList;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.demo.member.Member;


@Repository
public interface AlarmSenderDao extends JpaRepository<Alarmsender, Integer>{

}
