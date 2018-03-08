package com.ing.springboot.service;

import com.ing.springboot.util.ValidationException;

public interface AccountValidationService {
	//public CustomerDetails getCustomerInfo(int custid, int accountInfo);
	public boolean validateAccountNumber(Long accountNumber) throws ValidationException;
}
