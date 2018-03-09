package com.ing.springboot.service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ing.springboot.dao.CustomerServiceDao;
import com.ing.springboot.model.CustomerAccountDetails;
import com.ing.springboot.model.CustomerDetails;
import com.ing.springboot.util.ValidationException;

@Service
public class DepositMoneyServiceImpl implements DepositMoneyService{

	@Autowired
	CustomerServiceDao customerServiceDao;

	@Override
	public String depositeMoney(String custId, Double amount) throws ValidationException {
		CustomerDetails customerDetails = null;
		CustomerAccountDetails accountDetails = new CustomerAccountDetails();
		int rowUpdated=0;
		try{
			if(null == custId){
				throw new ValidationException ("Customer ID required");
			}else{
				customerDetails  = customerServiceDao.getBalanceByCustId(custId);
				accountDetails.setCustomerID(custId);
				accountDetails.setAccountNumber(customerDetails.getAccountNumber());
				accountDetails.setBalance(customerDetails.getBalance());
				accountDetails.setTransactionMode("C");
			}

			if(null!=amount && amount>0){
				if(null!=customerDetails.getBalance()){
					Double currentBalance=customerDetails.getBalance()+amount;
					rowUpdated=customerServiceDao.UpdateCustomerBalance(String.valueOf(custId),currentBalance);
					customerServiceDao.insertTransaction(accountDetails);	
				}

			}else{
				throw new ValidationException ("Amount can not be null and greater than zero");
			}
		}catch(ValidationException ex){

			throw new ValidationException("Exception occured while deposite money"+ex.getMessage());
		}
		if(rowUpdated>0){
			return "Deposite money transaction successfully updated";
		}else{
			return "Deposite money transaction Failed";
		}

	}

}
