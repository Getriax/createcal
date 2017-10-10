package com.demo.rest.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.demo.rest.model.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

}
