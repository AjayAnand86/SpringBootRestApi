package com.ing.springboot.handler;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class BankHandler
{
	public static String getDataForBank(String bankid)
	{
	    final String uri = "http://localhost:8444/PaymentsReportApplication/api/swift/v1/" + bankid;
	    
	    String plainCreds = "user1:secret1";
	    byte[] plainCredsBytes = plainCreds.getBytes();
	    byte[] base64CredsBytes = Base64.encodeBase64(plainCredsBytes);
	    String base64Creds = new String(base64CredsBytes);

	    HttpHeaders headers = new HttpHeaders();
	    headers.add("Authorization", "Basic " + base64Creds);
	    
	    RestTemplate restTemplate = new RestTemplate();
	    
	    HttpEntity<String> request = new HttpEntity<String>(headers);
	    ResponseEntity<String> response = restTemplate.exchange(uri, HttpMethod.GET, request, String.class);
	    	    
	    String result = response.getBody();
	    result = result.replaceAll("~n", System.getProperty("line.separator"));
		System.out.println("Received: " + result);
	    return result;
	}
}