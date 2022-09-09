package com.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.entities.User;

public interface IUserRepository extends JpaRepository<User, Long> {
	//login
	User findByEmailAndPassword(String email,String pass);
}
