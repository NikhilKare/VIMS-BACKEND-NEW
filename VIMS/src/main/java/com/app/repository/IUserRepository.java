package com.app.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.entities.User;

public interface IUserRepository extends JpaRepository<User, Long> {
	//login
	User findByEmailAndPassword(String email,String pass);
	Optional<User> findByEmail(String email);
	
}
