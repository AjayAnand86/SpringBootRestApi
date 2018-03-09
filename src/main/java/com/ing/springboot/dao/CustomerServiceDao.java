package com.ing.springboot.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import com.ing.springboot.model.CustomerAccountDetails;
import com.ing.springboot.model.CustomerDetails;
import com.ing.springboot.model.TransactionDetails;
import com.ing.springboot.model.CustomerTransactionDetails;

@Repository
public class CustomerServiceDao {
	
	@Value("${repo.insert.customer.details}")
	private static String INSERT_CUSTOMER_DETAILS;
	
	@Value("${repo.select.customer.details}")
	private static String SELECT_CUSTOMER_DETAILS;
	
	@Value("${repo.select.customer.balance.details}")
	private static String SELECT_CUSTOMER_BALANCE_BY_ID;
	
	@Value("${repo.insert.customer.transaction.details}")
	private static String INSERT_TRANSACTION;
	
	@Value("${repo.update.customer.transaction.details}")
	private static String UPDATE_TRANSACTION;
	
	@Value("${repo.select.customer.account.details}")
	private static String SELECT_CUSTOMER_ACCOUNT_DETAILS;
	
	public void insertCustomer (CustomerDetails CustomerDetails){

		String insertTableSQL = INSERT_CUSTOMER_DETAILS;
		Connection dbConnection =  ConnectDB.getConection();
		PreparedStatement preparedStatement;
		try {
			preparedStatement = dbConnection.prepareStatement(insertTableSQL);
			preparedStatement.setInt(1, CustomerDetails.getAccountNumber());
			preparedStatement.setString(2, CustomerDetails.getCustomerID());
			preparedStatement.setString(3, CustomerDetails.getCustomerName());
			preparedStatement.setString(4, CustomerDetails.getCustomerRole());
			preparedStatement.setString(5, CustomerDetails.getCustomerStatus());
			preparedStatement.setDouble(6, CustomerDetails.getBalance());
			preparedStatement.setString(7, CustomerDetails.getCurrencyCode());
			preparedStatement .executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public CustomerDetails getCustomerByCustId (
			String custId){
	String selectSQL = SELECT_CUSTOMER_DETAILS;
	Connection dbConnection =  ConnectDB.getConection();
	CustomerDetails customerDetails = null;
	
	try {
		PreparedStatement preparedStatement = dbConnection.prepareStatement(selectSQL);
		preparedStatement.setString(1, custId);
		ResultSet rs = preparedStatement.executeQuery();
		
		
		while (rs.next()) {
			customerDetails = new CustomerDetails();
			customerDetails.setAccountNumber(rs.getInt("ACCOUNT_NUMBER"));
			customerDetails.setCustomerID(rs.getString("CUSTOMER_ID"));
			customerDetails.setCustomerName(rs.getString("CUSTOMER_NAME"));
			customerDetails.setCustomerRole(rs.getString("CUSTOMER_ROLE"));
			customerDetails.setCustomerStatus(rs.getString("CUSTOMER_STATUS"));
			customerDetails.setBalance(rs.getDouble("AVAILABLE_BALANCE"));
			customerDetails.setCurrencyCode(rs.getString("CURR_CDE"));
			
		}
		
	}catch (SQLException se){
			se.printStackTrace();
		}

		return customerDetails;
	}

	public CustomerDetails getBalanceByCustId (String CustId){
		String selectSQL = SELECT_CUSTOMER_BALANCE_BY_ID;
		Connection dbConnection =  ConnectDB.getConection();
		CustomerDetails accountDetails = null;

		try {
			PreparedStatement preparedStatement = dbConnection.prepareStatement(selectSQL);
			preparedStatement.setString(1, CustId);
			ResultSet rs = preparedStatement.executeQuery();
			accountDetails = new CustomerDetails();
			while (rs.next()) {
				accountDetails.setAccountNumber(rs.getInt("ACCOUNT_NUMBER"));
				accountDetails.setCustomerID(rs.getString("CUSTOMER_ID"));
				accountDetails.setBalance(rs.getDouble("AVAILABLE_BALANCE"));
			}
		}catch (SQLException se){
			se.printStackTrace();
		}

		return accountDetails;
	}

	public void insertTransaction (CustomerAccountDetails accountDetails){

		String insertSQL = INSERT_TRANSACTION;
		Connection dbConnection =  ConnectDB.getConection();
		PreparedStatement preparedStatement;
		try {
			preparedStatement = dbConnection.prepareStatement(insertSQL);
			preparedStatement.setString(1, accountDetails.getCustomerID());
			preparedStatement.setInt(2, accountDetails.getAccountNumber());
			preparedStatement.setDouble(3, accountDetails.getBalance());
			preparedStatement.setString(4, accountDetails.getTransactionMode());
			preparedStatement.setDate(5, accountDetails.getTransactionDate());
			preparedStatement .executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public int UpdateCustomerBalance (String CustId, Double Balance){
		String updateSQL = UPDATE_TRANSACTION;
		Connection dbConnection =  ConnectDB.getConection();
		int count = 0;

		try {
			PreparedStatement preparedStatement = dbConnection.prepareStatement(updateSQL);
			preparedStatement.setString(1, CustId);
			preparedStatement.setDouble(2, Balance);
			count = preparedStatement.executeUpdate(updateSQL);
			
		}catch (SQLException se){
			se.printStackTrace();
		}
		return count;
	}
	
	public CustomerTransactionDetails getCustomerTransactionDetails(String custId, Date startDate, Date endDate) {
		String selectSQL = SELECT_CUSTOMER_ACCOUNT_DETAILS;

		Connection dbConnection = ConnectDB.getConection();

		CustomerTransactionDetails customerTransactionDetails = new CustomerTransactionDetails();

		customerTransactionDetails.setCustomerId(custId);

		List<TransactionDetails> transactionDetailsList = new ArrayList<TransactionDetails>();

		try {
			PreparedStatement preparedStatement = dbConnection.prepareStatement(selectSQL);
			preparedStatement.setString(1, custId);
			preparedStatement.setDate(2, (java.sql.Date) startDate);
			preparedStatement.setDate(3, (java.sql.Date) endDate);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				TransactionDetails transactionDetails = new TransactionDetails();
				transactionDetails.setCustomerId(custId);
				transactionDetails.setTransactionAmount(rs.getDouble("BALANCE"));
				transactionDetails.setTransactionType(rs.getString("TRANSACTION_MODE"));
				transactionDetailsList.add(transactionDetails);
			}
		} catch (SQLException se) {
			se.printStackTrace();
		} finally {
			try {
				dbConnection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return customerTransactionDetails;
	}
}

