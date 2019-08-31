package com.cap.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
@Table(name = "customer")
public class Customer {

	@Id
	@SequenceGenerator(name = "user_id", initialValue = 1000, sequenceName = "userSeq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_id")
	private int accountId;
	@Column(length = 15)
	@Pattern(regexp = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{7,15}", message = "Password should be the combination of digit,capital letters and small letters")
	private String password;
	@Column(length = 30)
	@Pattern(regexp = "[A-Z][a-z]{2,}", message = "First name should start with capital letter and has minimum length 3 chars")
	private String firstName;
	@Column(length = 30)
	@Pattern(regexp = "[A-Z][a-z]{3,}", message = "First name should start with capital letter and has minimum length 4 chars")
	private String lastName;
	private double balance;
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "customer", cascade = CascadeType.ALL)

	// Initialization required to avoid NullPointerException
	private List<Transaction> transactions = new ArrayList<Transaction>();

	public Customer() {
		super();

	}

	public Customer(String firstName, String lastName,String password, double amt) {

		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.balance = amt;

	}

	public List<Transaction> getTransactions() {
		return transactions;
	}

	public void setTransactions(List<Transaction> transactions) {
		this.transactions = transactions;
	}

	// the method below will add employee to department
	// also serves the purpose to avoid cyclic references.

	public void addTransactions(Transaction trans) {
		trans.setCustomer(this); // this will avoid nested cascade
		this.getTransactions().add(trans);
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	@Override
	public String toString() {
		return "Account Id :" + accountId + ", First Name :" + firstName + ",Last Name :" + lastName;
	}

	public int getAccountId() {
		return accountId;
	}

	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

}
