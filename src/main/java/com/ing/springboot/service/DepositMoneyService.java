package com.ing.springboot.service;

import com.ing.springboot.util.ValidationException;

public interface DepositMoneyService {
	public String depositeMoney(Long custID,Double amount) throws ValidationException;
}
