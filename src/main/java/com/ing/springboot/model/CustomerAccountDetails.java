package com.ing.springboot.model;

import java.sql.Date;

public class CustomerAccountDetails {
	
	private Integer customerID;
	private String accountNumber;
	private Double balance;
	private String transactionMode;
	private  Date transactionDate;
	

	public Date getTransactionDate() {
		return transactionDate;
	}

	public void setTransactionDate(Date transactionDate) {
		this.transactionDate = transactionDate;
	}

	public Integer getCustomerID() {
		return customerID;
	}

	public void setCustomerID(Integer customerID) {
		this.customerID = customerID;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public Double getBalance() {
		return balance;
	}

	public void setBalance(Double balance) {
		this.balance = balance;
	}

	public String getTransactionMode() {
		return transactionMode;
	}

	public void setTransactionMode(String transactionMode) {
		this.transactionMode = transactionMode;
	}

	@Override
	public String toString() {
		return "CustomerAccountDetails [customerID=" + customerID + ", accountNumber=" + accountNumber + ", balance="
				+ balance + ", transactionMode=" + transactionMode + "]";
	}

}
