package com.ing.springboot.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.stereotype.Service;

import com.ing.springboot.dao.CustomerServiceDao;
import com.ing.springboot.model.CustomerDetails;
import com.ing.springboot.model.CustomerTransactionDetails;
import com.ing.springboot.util.ValidationException;

@Service
public class AccountValidationServiceImpl implements AccountValidationService {

	CustomerServiceDao customerServiceDao = null;
	
	public AccountValidationServiceImpl() {
		customerServiceDao  = new CustomerServiceDao();
	}
	
	
	@Override
	public boolean validateAccountNumber(Long accountNumber) throws ValidationException {
		if(accountNumber == null)
			throw new ValidationException("Account number should not be null");
		
		if(accountNumber == 0){
			return Boolean.FALSE;
		}
		
		int accountLength = accountNumber.toString().length();
		int startDivisibleRange = (int) Math.pow(10, accountLength-1);
		double sum = 0;
		
		for(int i=0;i<accountLength;i++){
			double div = accountNumber/startDivisibleRange;
			double mod = div % 10;
			sum+= (mod*(accountLength-i));
			startDivisibleRange= startDivisibleRange/10;
		}
		if((sum%11) == 0)
			return true;
		
		return false;
	}

	@Override
	public CustomerDetails getCustomerDetails(final Long custId) throws ValidationException {
		CustomerDetails customerDetails = null;
		if(null == custId){
			throw new ValidationException ("Enter custId");
		}else{
			customerDetails  = new CustomerServiceDao().getCustomerByCustId(String.valueOf(custId));
		}
				
		if(null == customerDetails){
			throw new ValidationException ("No records found");
		}
		return customerDetails;
	}
	
	
	@Override
	public CustomerTransactionDetails getCustomerTransactionDetails(Long custId, String startDate, String endDate) throws ValidationException {

		if(custId == null || startDate == null || endDate == null )
		{
			throw new ValidationException("Missing Paramenteres: please enter customer ID, start date, end date");
		}
		
		SimpleDateFormat sdfmt2= new SimpleDateFormat("dd-MM-yyyy");
		Date startDt = null, endDt = null;
		try {
			startDt = sdfmt2.parse(startDate);
			endDt = sdfmt2.parse(endDate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new ValidationException("Invalid Date format: enter dd-MM-yyyy");
		}
		
		if(startDt.compareTo(endDt)>0)
		{
			throw new ValidationException("Invalid Date format: enter dd-MM-yyyy");
		}
		
		CustomerTransactionDetails custTrasactionDetails = null;
		try{
			custTrasactionDetails = customerServiceDao.getCustomerTransactionDetails(custId.toString(), startDt, endDt);
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return custTrasactionDetails;
	}
}
