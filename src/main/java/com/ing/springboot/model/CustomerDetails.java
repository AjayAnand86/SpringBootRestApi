package com.ing.springboot.model;

public class CustomerDetails {
	private Integer CustomerID;
	private String accountNumber;
	private String customerName;
	private String customerRole;
	private boolean active;
	private Double balance;

	public Integer getCustomerID() {
		return CustomerID;
	}

	public void setCustomerID(Integer customerID) {
		CustomerID = customerID;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getCustomerRole() {
		return customerRole;
	}

	public void setCustomerRole(String customerRole) {
		this.customerRole = customerRole;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public Double getBalance() {
		return balance;
	}

	public void setBalance(Double balance) {
		this.balance = balance;
	}

	@Override
	public String toString() {
		return "CustomerDetails [CustomerID=" + CustomerID + ", accountNumber=" + accountNumber + ", customerName="
				+ customerName + ", customerRole=" + customerRole + ", active=" + active + ", balance=" + balance + "]";
	}

}
