package com.example.demo.alarmtest.monitor;

import java.util.ArrayList;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.demo.member.Member;


@Repository
public interface MonitorDao extends JpaRepository<Monitor, Integer>{

}
