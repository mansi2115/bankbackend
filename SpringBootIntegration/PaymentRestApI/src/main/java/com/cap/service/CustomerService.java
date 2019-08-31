package com.cap.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cap.dao.ICustomerDao;
import com.cap.entities.Customer;
import com.cap.entities.Transaction;
import com.cap.exception.InvalidActivityException;
import com.cap.exception.InvalidInputException;

@Service
@Transactional
public class CustomerService implements ICustomerService {

	@Autowired
	private ICustomerDao custDao;

	Customer cust;

	@Override
	public int createAccount(Customer customer) {
		this.cust=null;
		Customer cust = custDao.save(customer);
		return cust.getAccountId();
	}

	@Override
	public double showBalance() {

		if (this.cust != null) {
			return this.cust.getBalance();
		}
		return 0;
	}

	@Override
	public double deposit(double amt) {

		if (this.cust != null) {
			if (amt <= 0) {
				throw new InvalidInputException("Amount should be greater than zero");
			}
			this.cust.setBalance(this.cust.getBalance() + amt);
			//custDao.save(this.cust);
			makeTransaction("Deposit", 0, 0, amt);
			return cust.getBalance();
		}
		throw new InvalidActivityException("Customer not logged in");
	}

	@Override
	public double withdraw(double amt) {

		if (this.cust != null) {
			if (amt > this.cust.getBalance() && amt <= 0) {
				throw new InvalidInputException("Insufficient balance");
			}
			this.cust.setBalance(this.cust.getBalance() - amt);
			custDao.save(this.cust);
			makeTransaction("Withdraw", 0, 0, amt);
			return this.cust.getBalance();
		}
		throw new InvalidActivityException("Customer not logged in");
	}

	@Override
	public String fundTransfer(int accountId1, double amt) {
		if (this.cust != null) {
			if (!custDao.existsById(accountId1)) {
				throw new InvalidInputException("Account does not exists");
			}
			
			int accountId = this.cust.getAccountId();
			if (accountId == accountId1) {
				throw new InvalidInputException("Customer cannot transfer money to same account");
			}
			// Customer sender= custDao.findById(accountId);
			
			if (amt > this.cust.getBalance()) {
				System.out.println(this.cust.getBalance());
				System.out.println("Amt"+amt);
				throw new InvalidInputException("Insufficient balance");
			}
			this.cust.setBalance(this.cust.getBalance() - amt);
		
			custDao.save(this.cust);
			Optional<Customer> receiver = custDao.findById(accountId1);
			Customer receiver1 = receiver.get();
		
			receiver1.setBalance(receiver1.getBalance() + amt);
		
			
			custDao.save(receiver1);
			makeTransaction("Deposit", accountId, accountId1, amt);
			return "Amount transferred successfully. Your balance is" + this.cust.getBalance();
		}
		throw new InvalidActivityException("Customer not logged in");
	}

	@Override
	public boolean authenticateUser(int accountId, String pwd) {
		if (!custDao.existsById(accountId)) {
			throw new InvalidInputException("Account does not exists");
		}

		Optional<Customer> cust1 = custDao.findById(accountId);
		Customer customer = cust1.get();
		String password = customer.getPassword();

		if (password.equals(pwd)) {

			this.cust = customer;
			return true;
		}

		return false;
	}

	@Override
	public List<Transaction> customerTransactions() {
		if (this.cust != null) {
			
			return this.cust.getTransactions();
		}
		throw new InvalidActivityException("Customer not logged in");
	}

	public void makeTransaction(String transactionType, int from, int to, double amt) {

		Transaction t1 = new Transaction(transactionType, from, to, amt);

		this.cust.addTransactions(t1);
		custDao.save(cust);
		

	}

}
