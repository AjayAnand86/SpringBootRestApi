package com.ing.springboot.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class MT940ParsedObject {
	private String sender;
	private String receiver;
	private String openingAmount;
	private String closingAmount;
	private String currency;
	private String accountNumber;
	private List<TransactionDetails> transactionDetails;

	public MT940ParsedObject() {
		transactionDetails = new ArrayList<TransactionDetails>();
	}

	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	public String getReceiver() {
		return receiver;
	}

	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}

	public String getOpeningAmount() {
		return openingAmount;
	}

	public void setOpeningAmount(String openingAmount) {
		this.openingAmount = openingAmount;
	}

	public String getClosingAmount() {
		return closingAmount;
	}

	public void setClosingAmount(String closingAmount) {
		this.closingAmount = closingAmount;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

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

	@Override
	public String toString() {
		return "MT940ParsedObject [sender=" + sender + ", receiver=" + receiver + ", openingAmount=" + openingAmount
				+ ", closingAmount=" + closingAmount + ", currency=" + currency + ", accountNumber=" + accountNumber
				+ ", transactionDetails=" + transactionDetails + "]";
	}

	

}
