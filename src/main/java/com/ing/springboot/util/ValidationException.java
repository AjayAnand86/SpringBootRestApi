package com.ing.springboot.util;

import org.springframework.stereotype.Component;

@Component
public class ValidationException extends Exception {
	public ValidationException(String msg)
	{
		super(msg);
	}
}
