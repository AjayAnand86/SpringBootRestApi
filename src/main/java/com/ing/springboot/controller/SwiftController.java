package com.ing.springboot.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ing.springboot.model.BankAccountDetail;
import com.ing.springboot.service.ReportService;
import com.ing.springboot.service.SwiftService;

@RestController
@RequestMapping("/api/swift")
public class SwiftController {
	

	public static final Logger logger = LoggerFactory.getLogger(ReportsController.class);

	@Autowired
	ReportService reportService;
	
	@Autowired
	SwiftService swiftService;
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/v1/{bankId}", method = RequestMethod.GET)
	public ResponseEntity<String> getSwiftFile(@PathVariable("bankId") String bankId)
	{
		
		if (bankId.trim().length() == 0)
		{
			return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
		}
		if(!bankId.equals("b1") && !bankId.equals("b2") && !bankId.equals("b3"))
		{
		
			return new ResponseEntity<String>("", HttpStatus.BAD_REQUEST);
		}
		try{
			return new ResponseEntity<String>(swiftService.getSwiftTransactionDetails(bankId),HttpStatus.OK);
		}catch(Exception e)
		{
			return new ResponseEntity<String>("Error while retrieving data",HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
