package com.cap.dao;



import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import com.cap.entities.Customer;



@Repository
public interface ICustomerDao extends JpaRepository<Customer, Integer> {
	
	
	
}
