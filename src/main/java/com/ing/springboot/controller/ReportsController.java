package com.ing.springboot.controller;

import java.io.File;
import java.io.FileInputStream;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ing.springboot.model.BankAccountDetail;
import com.ing.springboot.service.ReportService;

//@RestController
//@RequestMapping("/reports")
public class ReportsController
{
	/*public static final Logger logger = LoggerFactory.getLogger(ReportsController.class);

	@Autowired
	ReportService reportService;
	
	@RequestMapping(value = "/balance", method = RequestMethod.GET)
	public ResponseEntity<String> listAllBankAccountDetails()
	{
		List<BankAccountDetail> bankAccountDetail = reportService.getAllAccountPercentShare();
		if (bankAccountDetail.isEmpty())
		{
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
		//return new ResponseEntity<List<BankAccountDetail>>(bankAccountDetail, HttpStatus.OK);
		return new ResponseEntity<String>("jkbnjbjk", HttpStatus.OK);
	}*/
	
	
	
	/*@GET
	@Path("/pdf/{bankId}")
	@Produces("application/pdf")
	public javax.ws.rs.core.Response getPdf(@PathVariable("bankId") long bankId) throws Exception
	{
	    File file = new File("E:\\tmp\\"+bankId+".pdf");
	    FileInputStream fileInputStream = new FileInputStream(file);
	    javax.ws.rs.core.Response.ResponseBuilder responseBuilder = javax.ws.rs.core.Response.ok((Object) fileInputStream);
	    responseBuilder.type("application/pdf");
	    responseBuilder.header("Content-Disposition", "filename="+bankId+".pdf");
	    return responseBuilder.build();
	}*/
}
