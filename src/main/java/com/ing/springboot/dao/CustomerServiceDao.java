package com.ing.springboot.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.ing.springboot.model.CustomerDetails;

public class CustomerServiceDao {

	public void insertCustomer (CustomerDetails CustomerDetails){
		
		String insertTableSQL = "INSERT INTO CUSTOMER_DETAILS"
				+ "(ACCOUNT_NUMBER, CUSTOMER_ID, CUSTOMER_NAME, CUSTOMER_ROLE, CUSTOMER_STATUS,AVAILABLE_BALANCE) VALUES"
				+ "(?,?,?,?,?,?)";
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
			preparedStatement .executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public CustomerDetails getCustomerByCustId (
			String custId){
	String selectSQL = "SELECT ACCOUNT_NUMBER, CUSTOMER_ID, CUSTOMER_NAME, CUSTOMER_ROLE, CUSTOMER_STATUS,AVAILABLE_BALANCE FROM CUSTOMER_DETAILS WHERE CUSTOMER_ID = ?";
	Connection dbConnection =  ConnectDB.getConection();
	CustomerDetails customerDetails = null;
	
	try {
		PreparedStatement preparedStatement = dbConnection.prepareStatement(selectSQL);
		preparedStatement.setString(1, custId);
		ResultSet rs = preparedStatement.executeQuery();
		customerDetails = new CustomerDetails();
		while (rs.next()) {
			customerDetails.setAccountNumber(rs.getInt("ACCOUNT_NUMBER"));
			customerDetails.setCustomerID(rs.getString("CUSTOMER_ID"));
			customerDetails.setCustomerName(rs.getString("CUSTOMER_NAME"));
			customerDetails.setCustomerRole(rs.getString("CUSTOMER_ROLE"));
			customerDetails.setCustomerStatus(rs.getString("CUSTOMER_STATUS"));
			customerDetails.setBalance(rs.getDouble("AVAILABLE_BALANCE"));
			
		}
	}catch (SQLException se){
			se.printStackTrace();
		}

	return customerDetails;
	}
	
}
	
