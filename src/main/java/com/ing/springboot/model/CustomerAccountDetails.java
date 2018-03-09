package com.ing.springboot.model;

import java.sql.Date;

public class CustomerAccountDetails {

	private String customerID;
	private Integer accountNumber;
	private Double balance;
	private String transactionMode;
	private Date transactionDate;

	public String getCustomerID() {
		return customerID;
	}

	public void setCustomerID(String customerID) {
		this.customerID = customerID;
	}

	public Integer getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(Integer accountNumber) {
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

	public Date getTransactionDate() {
		return transactionDate;
	}

	public void setTransactionDate(Date transactionDate) {
		this.transactionDate = transactionDate;
	}

	@Override
	public String toString() {
		return "CustomerAccountDetails [customerID=" + customerID + ", accountNumber=" + accountNumber + ", balance="
				+ balance + ", transactionMode=" + transactionMode + ", transactionDate=" + transactionDate + "]";
	}

}
