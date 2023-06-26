package com.example.demo.notification.diary;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface NotifyDiaryDao extends JpaRepository<Notifydiary, Integer> {
		
	@Query(value="select member.name, b.title, c.content, d.insttime from member A, diaryboard B, diarycomment C, notifydiary C where id > :id and B.num = C.num and A.id = D.id order by C.w_date desc", nativeQuery=true)
	public ArrayList<Notifydiary> getAllDesc(@Param(value="id") String id);
}
