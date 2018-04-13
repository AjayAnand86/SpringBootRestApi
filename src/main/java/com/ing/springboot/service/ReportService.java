package com.ing.springboot.service;

import java.util.List;

import com.ing.springboot.model.BankAccountDetail;

public interface ReportService
{
	public List<BankAccountDetail> getAllAccountPercentShare();

	public BankAccountDetail bankAccountTrancationDetails(String bankId);
}
