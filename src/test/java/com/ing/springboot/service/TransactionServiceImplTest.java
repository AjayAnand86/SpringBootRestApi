package com.ing.springboot.service;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import com.ing.springboot.service.*;
import com.ing.springboot.service.TransactionService;
import com.ing.springboot.util.ValidationException;

public class TransactionServiceImplTest {
	
	TransactionService transactionService;
	
		@Before
		public void setUp() throws Exception {
			transactionService = new TransactionServiceImpl();
		}

		@Test (expected = ValidationException.class)
		public void testDepositeMoneyForNullCustIDNullAmount() throws ValidationException {
			transactionService.depositeMoney(null, null);
		}
		
		
		@Test (expected = ValidationException.class)
		public void testWithdrawMoneyForNullCustIDNullAmount() throws ValidationException {
			transactionService.withdrawMoney(null, null);
		}

}
