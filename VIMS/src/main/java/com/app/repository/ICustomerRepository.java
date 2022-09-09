package com.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.entities.Customer;

public interface ICustomerRepository extends JpaRepository<Customer, Long>{

}
