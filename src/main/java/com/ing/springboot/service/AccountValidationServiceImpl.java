package com.ing.springboot.service;

import org.springframework.stereotype.Service;

import com.ing.springboot.dao.CustomerServiceDao;
import com.ing.springboot.model.CustomerDetails;
import com.ing.springboot.util.ValidationException;

@Service
public class AccountValidationServiceImpl implements AccountValidationService {

	//@Autowired
	
	
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
	
}
