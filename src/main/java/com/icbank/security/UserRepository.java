package com.icbank.security;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.icbank.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

	@Query("select u from User u order by u.firstName")
	List<User> findAll();
}
