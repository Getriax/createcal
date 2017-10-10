package com.demo.rest.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.demo.rest.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
	User findByUsername(String username);
	@Query("SELECT u.id FROM User u WHERE u.username = :username")
	Long findIdOfUserByName(@Param("username") String username);
}
