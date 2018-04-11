package com.ing.springboot.service;

import java.io.IOException;

import org.springframework.stereotype.Service;

import com.ing.springboot.util.ClasspathFileReader;

@Service("swiftService")
public class SwiftServiceImpl implements SwiftService{

	
	@Override
	public String getSwiftTransactionDetails(String bankId) throws IOException {
		// TODO Auto-generated method stub
		return new ClasspathFileReader().readFileFromClasspath(bankId);
	}

}
