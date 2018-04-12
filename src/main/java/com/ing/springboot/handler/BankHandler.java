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
		System.out.println("Received: " + result);
	    System.out.println("Received: " + "{1:F01AAAABB99BSMK3513951576}{2:O9400934081223BBBBAA33XXXX03592332770812230834N}{4:\n:20:0112230000000890\n:25:SAKG800030155USD\n:28C:255/1\n:60F:C011223USD175768,92\n:61:0112201223CD110,92SALANONREF//08 IL053309/GB/2542049/SHS/312,\n:62F:C011021USD175879,84\n:20:NONREF\n:25:4001400010\n:28C:58/1\n:60F:C140327EUR6308,75\n:61:1403270327C3519,76FORX50RS201403240008//2014032100037666ABC DO BRASIL LTDA\n:86:INVOICE NR. 6000012801 \nORDPRTY : ABC DO BRASIL LTDA RUA LIBERO BADARO,293-SAO \nPAULO BRAZIL }");
	    return result.toString();
	}
}