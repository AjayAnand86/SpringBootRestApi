package com.ing.springboot.service;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import junit.framework.AssertionFailedError;

public class CustomerServiceImplTest {
	
	CustomerService customerService;

	@Before
	public void setUp() throws Exception {
		customerService = new CustomerServiceImpl();
	}

	@Test
	public void testFindAllUsers() {
		assertNotNull(customerService.findAllUsers());
	}

	@Test
	public void testFindByIdWithZero() {
		assertNull(customerService.findById(0));
	}
	
	@Test
	public void testFindByIdWithValid() {
		assertNull(customerService.findById(30));
	}

	@Test
	public void testFindByNameForNull() {
		assertNull(customerService.findByName(null));
	}

	@Test
	public void testFindByNameForValid() {
		assertEquals("Sam", customerService.findByName("Sam").getName());
	}


	@Test
	public void testDeleteUserById() {
		try {
			customerService.deleteUserById(0);
		} catch (Exception e) {
			fail("Exception encountered in Delete user test");
		}
	}

	

}
