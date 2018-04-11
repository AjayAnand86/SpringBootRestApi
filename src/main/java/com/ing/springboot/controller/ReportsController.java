package com.ing.springboot.controller;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
