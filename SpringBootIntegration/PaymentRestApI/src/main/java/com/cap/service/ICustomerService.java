package com.cap.service;

import java.util.List;

import com.cap.entities.Customer;
import com.cap.entities.Transaction;
import com.cap.exception.InvalidActivityException;
import com.cap.exception.InvalidInputException;

public interface ICustomerService {
	
	int createAccount(Customer customer);
	 double showBalance(); 
	 double deposit(double amt) ;
	 double withdraw(double amt) ;
	 String fundTransfer(int accountId1, double amt) ;
	 boolean authenticateUser(int accountId, String pwd) ;
	
	 List<Transaction> customerTransactions() ;

}
