package com.cap.controller;

import java.util.HashMap;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cap.entities.Customer;
import com.cap.entities.Transaction;
import com.cap.exception.InvalidActivityException;
import com.cap.exception.InvalidInputException;
import com.cap.service.ICustomerService;

//controller class
@RestController
@RequestMapping("/bank")
@CrossOrigin("http://localhost:4200")
public class BankServiceController {

	@Autowired
	ICustomerService service;
	
	//create customer
	@PostMapping(value = "/create")
	public ResponseEntity<RequestResponse> createAccount(@Valid @RequestBody final Customer customer) {

		int accountId = service.createAccount(customer);
		RequestResponse result = new RequestResponse(true,"Your account id is : " + accountId);
		return new ResponseEntity<RequestResponse>(result, HttpStatus.OK);

	}
//deposit amount
	@PutMapping(value = "/deposit")

	public  ResponseEntity<RequestResponse> depositAmount(@RequestBody final Customer customer) {

		double balance= service.deposit(customer.getBalance());

		RequestResponse result = new RequestResponse(true,"Your current balance is : " + balance);
	
		return new ResponseEntity<RequestResponse>(result, HttpStatus.OK);
	}
	//withdraw amount
	@PutMapping(value = "/withdraw")

	public  ResponseEntity<RequestResponse> withdrawAmount0(@RequestBody final Customer customer)
			throws InvalidInputException, InvalidActivityException {

		double balance= service.withdraw(customer.getBalance());
		RequestResponse result = new RequestResponse(true,"Your current balance is : " + balance);
		return new ResponseEntity<RequestResponse>(result, HttpStatus.OK);

	}

	@GetMapping(value = "/balance")

	public  ResponseEntity<RequestResponse> showBalance() {

		double balance= service.showBalance();
		RequestResponse result = new RequestResponse(true,"Your current balance is : " + balance);
		return new ResponseEntity<RequestResponse>(result, HttpStatus.OK);
	}

	@PutMapping(value = "/fundtransfer")

	public ResponseEntity<RequestResponse> fundTransfer( @RequestBody final Customer customer)
			throws InvalidInputException, InvalidActivityException {

		String message= service.fundTransfer(customer.getAccountId(), customer.getBalance());
		RequestResponse result = new RequestResponse(true, message);
		return new ResponseEntity<RequestResponse>(result , HttpStatus.OK);
	}

	@GetMapping(value = "/transactions")

	public  ResponseEntity<RequestResponse> printTransaction() {

		RequestResponse result = new RequestResponse(true,service.customerTransactions());
		return new ResponseEntity<RequestResponse>(result, HttpStatus.OK);
	}

	@PostMapping(value = "/authenticate")

	public  ResponseEntity<RequestResponse> signIn(@RequestBody final Customer customer)
			throws InvalidInputException {
		RequestResponse result ;
		boolean success=service.authenticateUser(customer.getAccountId(), customer.getPassword());
		
//		 if(service.authenticateUser(customer.getAccountId(), customer.getPassword())) {
//			 result  = new RequestResponse(true,"Successfully logged in");
//			 return new ResponseEntity<RequestResponse>(result, HttpStatus.OK);
//		 }
		 result  = new RequestResponse(true,success);
		 return new  ResponseEntity<RequestResponse>(result, HttpStatus.OK);
	}

}
