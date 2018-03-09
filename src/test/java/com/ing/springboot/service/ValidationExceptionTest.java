package com.ing.springboot.service;

import static org.junit.Assert.*;

import org.junit.Test;

import com.ing.springboot.util.ValidationException;

public class ValidationExceptionTest {
	
	AccountValidationServiceImpl accountValidationServiceImpl = new AccountValidationServiceImpl();
	
	@Test
	public void testValidateAccountNumberTestWithNull() throws ValidationException {
		try{
			accountValidationServiceImpl.validateAccountNumber(null);
		} catch(ValidationException e){
			assertTrue(true);
		}
	}
	
	@Test
	public void testValidateAccountNumberTestWithZero() throws ValidationException {
			assertEquals(Boolean.FALSE, accountValidationServiceImpl.validateAccountNumber(0l));
			
	}
	
	
	@Test
	public void testValidateAccountNumberTestWithValid() throws ValidationException {
			assertEquals(Boolean.TRUE, accountValidationServiceImpl.validateAccountNumber(736160221l));
			
	}
	
	@Test
	public void testValidateAccountNumberTestWithInValid() throws ValidationException {
			assertEquals(Boolean.FALSE, accountValidationServiceImpl.validateAccountNumber(736160222l));
			
	}
}
