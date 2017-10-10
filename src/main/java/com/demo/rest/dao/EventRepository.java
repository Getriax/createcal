package com.demo.rest.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.demo.rest.model.Event;
@Repository
public interface EventRepository extends JpaRepository<Event, Long>{
	@Query("SELECT e FROM Event e JOIN e.user u WHERE e.tag LIKE %:tag% AND u.id = :uid"  )
	List<Event> findByTagAndUser(@Param("uid") Long uid, @Param("tag") String tag);
	@Query("SELECT e FROM Event e JOIN e.user u WHERE u.id = :uid")
	List<Event> findByUserId(@Param("uid") Long uid);
	
	
}
