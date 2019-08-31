package com.cap.entities;

import java.text.SimpleDateFormat;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "Transaction")
public class Transaction {

	@Id
	@SequenceGenerator(name = "trans_id", initialValue = 2000, sequenceName = "tranSeq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "trans_id")
	private int transactionId;
	private String transDate;
	private String transTime;
	private String transactionType;
	private int fromAccount;
	private int toAccount;
	private double amount;
	
/*Applying bidirectional relation with customer*/
	@ManyToOne
	@JoinColumn(name = "cust_id")
	private Customer customer;

	public Transaction() {
		super();
		SimpleDateFormat sd = new SimpleDateFormat("dd/MM/yyyy ");
		SimpleDateFormat sd1 = new SimpleDateFormat("HH:mm:ss");
		this.transDate = sd.format(new Date());
		this.transTime = sd1.format(new Date().getTime());
	}

	public Transaction(String transactionType, int fromAccount, int toAccount, double amount) {
		SimpleDateFormat sd = new SimpleDateFormat("dd/MM/yyyy ");
		SimpleDateFormat sd1 = new SimpleDateFormat("HH:mm:ss");
		this.transDate = sd.format(new Date());
		this.transTime = sd1.format(new Date().getTime());
		this.transactionType = transactionType;
		this.fromAccount = fromAccount;
		this.toAccount = toAccount;
		this.amount = amount;
	}

	public int getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(int transactionId) {
		this.transactionId = transactionId;
	}

	public String getDate() {
		return transDate;
	}

	public void setDate(String date1) {
		this.transDate = date1;
	}

	public String getTime() {
		return transTime;
	}

	public void setTime(String time) {
		this.transTime = time;
	}

	public String getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}

	public int getFromAccount() {
		return fromAccount;
	}

	public void setFromAccount(int fromAccount) {
		this.fromAccount = fromAccount;
	}

	public int getToAccount() {
		return toAccount;
	}

	public void setToAccount(int toAccount) {
		this.toAccount = toAccount;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

//	public Customer getCustomer() {
//		return customer;
//	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	@Override
	public String toString() {
		return "     " + transactionId + "          " + transDate + "       " + transTime + "          "
				+ transactionType + "        " + fromAccount + "      " + toAccount + "      " + amount;

	}

}
