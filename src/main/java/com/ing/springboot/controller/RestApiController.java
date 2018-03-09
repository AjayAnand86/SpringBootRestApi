package com.ing.springboot.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired
;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ing.springboot.model.CustomerDetails;
import com.ing.springboot.model.CustomerTransactionDetails;
import com.ing.springboot.service.AccountValidationService;
import com.ing.springboot.service.DepositMoneyService;
import com.ing.springboot.util.ValidationException;



@RestController
@RequestMapping("/api")
public class RestApiController {

	public static final Logger logger = LoggerFactory.getLogger(RestApiController.class);

	@Autowired
	AccountValidationService accountService; 
	
	@Autowired
	DepositMoneyService depositService;

	@RequestMapping(value = "/user", method = RequestMethod.GET)
	public ResponseEntity<String> validateAccountNumber() throws ValidationException {
	
	//Boolean isValid = accountService.validateAccountNumber(accountNumber);
		return new ResponseEntity<String>("Working", HttpStatus.OK);
	}
	
	// -------------------Retrieve All Users---------------------------------------------

	@RequestMapping(value = "/validateAccount/{accountNumber}", method = RequestMethod.POST)
	public ResponseEntity<Boolean> validateAccountNumber(@PathVariable("accountNumber") long accountNumber) throws ValidationException {
		Boolean isValid = accountService.validateAccountNumber(accountNumber);
		return new ResponseEntity<Boolean>(isValid, HttpStatus.OK);
	}
	
	
	@RequestMapping(value = "/customerDetails/{custId}", method = RequestMethod.GET)
	public ResponseEntity<CustomerDetails> getCustomerDetails(@PathVariable("custId") long custId) throws ValidationException {
		CustomerDetails CustomerDetails  = accountService.getCustomerDetails(custId);
		return new ResponseEntity<CustomerDetails>(CustomerDetails, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/getBalanceReport/{custId}/{startDate}/{endDate}", method = RequestMethod.GET)
	public ResponseEntity<CustomerTransactionDetails> getCustomerDetails(@PathVariable("custId") long custId, @PathVariable("startDate") String startDate, @PathVariable("endDate") String endDate) throws ValidationException {
		CustomerTransactionDetails customerTransactionDetails  = accountService.getCustomerTransactionDetails(custId, startDate, endDate);
		
		HttpStatus status = HttpStatus.OK;
		if(customerTransactionDetails == null) {
			status =HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<CustomerTransactionDetails>(customerTransactionDetails, status);
	}
		
	@RequestMapping(value = "/depositAmount/{custId}/{amount}/", method = RequestMethod.PUT)
	public ResponseEntity<String> getCustomerDetails(@PathVariable("custId") String custId, @PathVariable("amount") Double amount) throws ValidationException {
		String depositStatus  = depositService.depositeMoney(custId, amount);
		
		HttpStatus status = HttpStatus.OK;
		if(depositStatus == null) {
			status =HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<String>(depositStatus, status);
	}
}