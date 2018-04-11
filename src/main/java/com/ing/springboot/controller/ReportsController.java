package com.ing.springboot.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ing.springboot.model.BankAccountDetail;
import com.ing.springboot.service.ReportService;

@RestController
@RequestMapping("/api/reports")
public class ReportsController
{
	public static final Logger logger = LoggerFactory.getLogger(ReportsController.class);

	@Autowired
	ReportService reportService;
	
	@RequestMapping(value = "/accountPercentShare/", method = RequestMethod.GET)
	public ResponseEntity<List<BankAccountDetail>> listAllBankAccountDetails()
	{
		List<BankAccountDetail> bankAccountDetail = reportService.getAllAccountPercentShare();
		if (bankAccountDetail.isEmpty())
		{
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<BankAccountDetail>>(bankAccountDetail, HttpStatus.OK);
	}
}
