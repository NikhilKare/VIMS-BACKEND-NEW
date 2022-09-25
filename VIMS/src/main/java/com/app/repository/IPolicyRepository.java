package com.app.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.app.entities.InsuranceProvider;
import com.app.entities.Policy;

public interface IPolicyRepository extends JpaRepository<Policy, Long>{

	
	@Query(value = "select * from Policy p where p.provider_user_id=?1",nativeQuery = true)
	List<Policy> findByProviderId(long id,Pageable page);
	
	
	long count();
	
	
	@Query(value = "select count(*) from Policy p where provider_user_id=?1",nativeQuery = true)
	long countByProvider(long ProviderId);
	
}
