package com.ing.springboot.service;

import com.ing.springboot.util.ValidationException;

public class AccountValidationServiceImpl implements AccountValidationService{

	@Override
	public boolean validateAccountNumber(Long accountNumber) throws ValidationException {
		// TODO Auto-generated method stub
		if(accountNumber == null)
			// TODO
			throw new ValidationException("Account number should not be null");
		
		if(accountNumber == 0){
			return Boolean.FALSE;
		}
		
		int accountLength = accountNumber.toString().length();
		int startDivisibleRange = (int) Math.pow(10, accountLength-1);
		double sum = 0;
		int count = 0;
		
				
		
		for(int i=0;i<accountLength;i++)
		{
			double div = accountNumber/startDivisibleRange;
			double mod = div % 10;
			sum+= (mod*(accountLength-i));
			startDivisibleRange= startDivisibleRange/10;
			System.out.println("startWithDivisible="+ startDivisibleRange + " mod=" + mod +" sum="+sum);
		}
		System.out.println("sum="+sum);
		if((sum%11) == 0)
			return true;
		
		return false;
	}
	
	public static void main(String []args) {
		AccountValidationServiceImpl ac = new AccountValidationServiceImpl();
		try {
			ac.validateAccountNumber(736160221l);
		} catch (ValidationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

		
}
