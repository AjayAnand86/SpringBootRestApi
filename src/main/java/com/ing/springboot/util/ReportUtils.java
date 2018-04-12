package com.ing.springboot.util;
import java.math.BigDecimal;
import java.util.List;

import com.ing.springboot.model.BankAccountDetail;

public class ReportUtils
{

	public static List<BankAccountDetail> calculatePercentShare(List<BankAccountDetail> bankAccountDetailList)
	{
		double totalBalanceInAllAccounts = bankAccountDetailList.stream().mapToDouble((o -> o.getClosingBalance())).sum();
		
		for (BankAccountDetail bankAccountDetail : bankAccountDetailList)
		{
			double percentForAccount = (float) (bankAccountDetail.getClosingBalance()*100)/totalBalanceInAllAccounts;
			bankAccountDetail.setPercentShare(percentForAccount);
		}
		
		return bankAccountDetailList;
	}
}
