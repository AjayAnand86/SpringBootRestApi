package com.ing.springboot.model;

import java.util.List;

public class BankAccountDetail
{

	private String accountNumber;
	private String bankName;
	private double closingBalance;
	private double percentShare;
	private List<TransactionDetails> transactionDetails;
	
	
	public List<TransactionDetails> getTransactionDetails() {
		return transactionDetails;
	}
	public void setTransactionDetails(List<TransactionDetails> transactionDetails) {
		this.transactionDetails = transactionDetails;
	}
	public String getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}
	public String getBankName() {
		return bankName;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	public double getClosingBalance() {
		return closingBalance;
	}
	public void setClosingBalance(double closingBalance) {
		this.closingBalance = closingBalance;
	}
	public double getPercentShare() {
		return percentShare;
	}
	public void setPercentShare(double percentShare) {
		this.percentShare = percentShare;
	}
}