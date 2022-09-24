package com.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.app.entities.InsuranceProvider;

public interface IProviderRepository extends JpaRepository<InsuranceProvider, Long>{

	List<InsuranceProvider> findAllByCompanyName(String companyName);

	@Query(" select distinct p.companyName from InsuranceProvider p")
	List<String> getAllCompanyNames();
}
