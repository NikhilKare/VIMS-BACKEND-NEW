package com.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.entities.Policy;

public interface IPolicyRepository extends JpaRepository<Policy, Long>{
	
}
