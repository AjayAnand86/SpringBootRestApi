package com.ing.springboot.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.ing.springboot.model.BankAccountDetail;
import com.ing.springboot.util.ReportUtils;

@Service("reportService")
public class ReportServiceImpl implements ReportService
{
	private static List<BankAccountDetail> bankAccountDetailList;
	
	static
	{
		bankAccountDetailList = populateDummyAccountDetails();
	}

	@Override
	public List<BankAccountDetail> getAllAccountPercentShare()
	{
		//List<BankAccountDetail> bankAccountDetailList = new ArrayList<>();
		bankAccountDetailList = ReportUtils.calculatePercentShare(bankAccountDetailList);
		return bankAccountDetailList;
	}

	private static List<BankAccountDetail> populateDummyAccountDetails()
	{
		List<BankAccountDetail> bankAccountDetailList = new ArrayList<>();
		
		BankAccountDetail b1 = new BankAccountDetail();
		b1.setAccountNumber("123456789");
		b1.setBankName("BankNetherland");
		b1.setClosingBalance(4568L);
		bankAccountDetailList.add(b1);
		
		BankAccountDetail b2 = new BankAccountDetail();
		b2.setAccountNumber("456789123");
		b2.setBankName("BankBelgium");
		b2.setClosingBalance(15876L);
		bankAccountDetailList.add(b2);
		
		BankAccountDetail b3 = new BankAccountDetail();
		b3.setAccountNumber("789123456");
		b3.setBankName("BankLBerg");
		b3.setClosingBalance(29556L);
		bankAccountDetailList.add(b3);
		return bankAccountDetailList;
	}
}