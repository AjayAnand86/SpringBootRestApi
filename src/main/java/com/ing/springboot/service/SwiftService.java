package com.ing.springboot.service;

import java.io.IOException;

public interface SwiftService {
	public String getSwiftTransactionDetails(String bankId) throws IOException;
}
