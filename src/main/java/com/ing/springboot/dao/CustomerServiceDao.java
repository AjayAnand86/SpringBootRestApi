package com.ing.springboot.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.ing.springboot.model.CustomerDetails;
import com.ing.springboot.model.CustomerTransactionDetails;
import com.ing.springboot.model.TransactionDetails;

@Repository
public class CustomerServiceDao {

	public void insertCustomer (CustomerDetails CustomerDetails){

		String insertTableSQL = "INSERT INTO CUSTOMER_DETAILS"
				+ "(ACCOUNT_NUMBER, CUSTOMER_ID, CUSTOMER_NAME, CUSTOMER_ROLE, CUSTOMER_STATUS,AVAILABLE_BALANCE,CURR_CDE) VALUES"
				+ "(?,?,?,?,?,?.?)";
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
	String selectSQL = "SELECT ACCOUNT_NUMBER, CUSTOMER_ID, CUSTOMER_NAME, CUSTOMER_ROLE, CUSTOMER_STATUS,AVAILABLE_BALANCE,CURR_CDE FROM CUSTOMER_DETAILS WHERE CUSTOMER_ID = ?";
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
		String selectSQL = "SELECT ACCOUNT_NUMBER, CUSTOMER_ID,AVAILABLE_BALANCE FROM CUSTOMER_DETAILS WHERE CUSTOMER_ID = ?";
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

	public int UpdateCustomerBalance (String CustId, Double Balance){
		String updateSQL = "UPDATE CUSTOMER_DETAILS SET AVAILABLE_BALANCE = ? WHERE CUSTOMER_ID = ?";
		Connection dbConnection =  ConnectDB.getConection();
		int count = 0;

		try {
			PreparedStatement preparedStatement = dbConnection.prepareStatement(updateSQL);
			preparedStatement.setString(2, CustId);
			preparedStatement.setDouble(1, Balance);
			count = preparedStatement.executeUpdate();
			
		}catch (SQLException se){
			se.printStackTrace();
		}
		return count;
	}
	
	public CustomerTransactionDetails getCustomerTransactionDetails(String custId, Date startDate, Date endDate) {
		String selectSQL = "SELECT * FROM CUSTOMER_ACCOUNT_DETAILS WHERE CUSTOMER_ID = ? AND TRANSACTION_DATE >=? AND TRANSACTION_DATE <=?";

		Connection dbConnection = ConnectDB.getConection();

		CustomerTransactionDetails customerTransactionDetails = new CustomerTransactionDetails();

		CustomerDetails customerDetails = getCustomerByCustId(custId);
		customerTransactionDetails.setCustomerId(custId);
		customerTransactionDetails.setCustomerName(customerDetails.getCustomerName());
		customerTransactionDetails.setCustomerStatus(customerDetails.getCustomerStatus());
		customerTransactionDetails.setStartDate(startDate);
		customerTransactionDetails.setEndDate(endDate);

		List<TransactionDetails> transactionDetailsList = new ArrayList<TransactionDetails>();

		try {
			PreparedStatement preparedStatement = dbConnection.prepareStatement(selectSQL);
			preparedStatement.setString(1, custId);
			preparedStatement.setDate(2, new java.sql.Date( startDate.getTime()));
			preparedStatement.setDate(3, new java.sql.Date(endDate.getTime()));

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				TransactionDetails transactionDetails = new TransactionDetails();
				transactionDetails.setId(custId);
				transactionDetails.setTransactionAmount(String.valueOf(rs.getDouble("BALANCE")));
				transactionDetails.setTransactionType(rs.getString("TRANSACTION_MODE"));
				transactionDetailsList.add(transactionDetails);
				customerTransactionDetails.setTransactionDetailsList(transactionDetailsList);
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

