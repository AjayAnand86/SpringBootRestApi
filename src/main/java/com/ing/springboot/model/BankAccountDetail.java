package com.ing.springboot.model;

public class BankAccountDetail
{

	private String accountNumber;
	private String bankName;
	private long closingBalance;
	private float percentShare;
	
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
	public long getClosingBalance() {
		return closingBalance;
	}
	public void setClosingBalance(long closingBalance) {
		this.closingBalance = closingBalance;
	}
	public float getPercentShare() {
		return percentShare;
	}
	public void setPercentShare(float percentShare) {
		this.percentShare = percentShare;
	}
}