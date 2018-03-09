package com.ing.springboot.service;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.ing.springboot.util.ValidationException;

public class AccountValidationServiceImplTest {
	
	AccountValidationService accountValidationService;
	
	@Before
	public void setUp() throws Exception {
		accountValidationService = new AccountValidationServiceImpl();
	}

	@Test (expected = ValidationException.class)
	public void testValidateAccountNumberForNull() throws ValidationException {
		accountValidationService.validateAccountNumber(null);
	}
	
	@Test
	public void testValidateAccountNumberForZero() throws ValidationException {
		assertEquals(Boolean.FALSE, accountValidationService.validateAccountNumber(0l));
	}
	
	@Test
	public void testValidateAccountNumberForValid() throws ValidationException {
		assertEquals(Boolean.TRUE, accountValidationService.validateAccountNumber(736160221l));
	}
	
	@Test
	public void testValidateAccountNumberForInValid() throws ValidationException {
		assertEquals(Boolean.FALSE, accountValidationService.validateAccountNumber(736160222l));
	}

	@Test (expected = ValidationException.class)
	public void testGetCustomerDetailsForNull() throws ValidationException {
		accountValidationService.getCustomerDetails(null);
	}
	
	@Test (expected = NullPointerException.class)
	public void testGetCustomerDetailsForValid() throws ValidationException {
		accountValidationService.getCustomerDetails(123l);
	}

}
