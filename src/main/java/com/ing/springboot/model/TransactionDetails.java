package com.ing.springboot.model;

public class TransactionDetails {

	private String transactionAmount;
	private String transactionType;
	private String id;

	public String getTransactionAmount() {
		return transactionAmount;
	}

	public void setTransactionAmount(String transactionAmount) {
		this.transactionAmount = transactionAmount;
	}

	public String getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "TransactionDetails [transactionAmount=" + transactionAmount + ", transactionType=" + transactionType
				+ ", id=" + id + "]";
	}

}
