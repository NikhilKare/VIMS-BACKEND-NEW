package com.app.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.app.entities.User;

public interface IUserRepository extends JpaRepository<User, Long> {
	//login
	User findByEmailAndPassword(String email,String pass);
	Optional<User> findByEmail(String email);
	@Query("select u.userName from User u")				
	List<String> getUserName();
	@Query("select u.email from User u")				
	List<String> getEmails();
	@Query("update User u set u.password=?2 where u.email=?1")
	@Modifying
	int changePassword(String email, String encode);
	User getByEmail(String email);
	
	@Query("update User u set u.password=?1 where u.userId=?2")
	@Modifying(flushAutomatically = true)
	int changePassword( String encode,long id);
		
	
	
}
