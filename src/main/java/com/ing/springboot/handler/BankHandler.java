package com.ing.springboot.handler;

import org.springframework.web.client.RestTemplate;

public class BankHandler
{
	public static String getDataForBank(String bankid)
	{
	    final String uri = "http://localhost:8080/api/swift/v1/" + bankid;
	     
	    RestTemplate restTemplate = new RestTemplate();
	    String result = restTemplate.getForObject(uri, String.class);
	    System.out.println("Received MT940 for bank: " + bankid + " is: " + result);
	    return result;
	}
}