package com.ing.springboot.model;

import java.util.Date;
import java.util.List;

public class CustomerTransactionDetails {

	private String customerId;
	private String customerName;
	private String customerStatus;
	private Date startDate;
	private Date endDate;
	private List<TransactionDetails> transactionDetailsList;
	private String errorMessage;
	public String getCustomerId() {
		return customerId;
	}
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public List<TransactionDetails> getTransactionDetailsList() {
		return transactionDetailsList;
	}
	public void setTransactionDetailsList(List<TransactionDetails> transactionDetailsList) {
		this.transactionDetailsList = transactionDetailsList;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getCustomerStatus() {
		return customerStatus;
	}
	public void setCustomerStatus(String customerStatus) {
		this.customerStatus = customerStatus;
	}
	
	
}
