package com.ing.springboot.service;

import com.ing.springboot.util.ValidationException;

public interface TransactionService {
	public String depositeMoney(String custID,Double amount) throws ValidationException;
	public String withdrawMoney(String custID,Double amount) throws ValidationException;
	
}
