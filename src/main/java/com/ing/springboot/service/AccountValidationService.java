package com.ing.springboot.service;

import com.ing.springboot.model.CustomerDetails;
import com.ing.springboot.util.ValidationException;

public interface AccountValidationService {
	public boolean validateAccountNumber(Long accountNumber) throws ValidationException;
	public CustomerDetails getCustomerDetails(Long custId) throws ValidationException;
}
