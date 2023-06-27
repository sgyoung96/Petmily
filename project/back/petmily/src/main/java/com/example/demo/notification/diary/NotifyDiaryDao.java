package com.example.demo.notification.diary;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository
public interface NotifyDiaryDao extends JpaRepository<Notifydiary, Integer> {
		
	@Query(value="select A.id, D.num, A.name, B.title, D.content, D.w_date, D.is_clicked from member A, diaryboard B, diarycomment C, notifydiary D where A.id = :id and B.num = C.num order by C.w_date desc", nativeQuery=true)
	@Transactional
	public ArrayList<Notifydiary> getAllDesc(@Param(value="id") String id);
}
