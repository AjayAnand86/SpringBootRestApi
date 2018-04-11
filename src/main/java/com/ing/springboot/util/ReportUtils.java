package com.ing.springboot.util;
import java.util.List;

public class ReportUtils
{

	public static List<BankAccountDetail> calculatePercentShare(List<BankAccountDetail> bankAccountDetailList)
	{
		long totalBalanceInAllAccounts = bankAccountDetailList.stream().mapToLong(o -> o.getClosingBalance()).sum();
		
		for (BankAccountDetail bankAccountDetail : bankAccountDetailList)
		{
			float percentForAccount = (float) (bankAccountDetail.getClosingBalance()*100)/totalBalanceInAllAccounts;
			bankAccountDetail.setPercentShare(percentForAccount);
		}
		
		return bankAccountDetailList;
	}
}
