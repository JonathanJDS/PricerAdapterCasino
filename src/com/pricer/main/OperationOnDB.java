package com.pricer.main;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.pricer.model.JDBCConnector;
import com.pricer.product.ProductBase;



public class OperationOnDB {
	
	static Logger logger = Logger.getLogger(Start.class);
	
	
	private Connection connection;

	public OperationOnDB() {


		//System.out.println("operation on DB initialisation.....");
		JDBCConnector jdbcconnector = new JDBCConnector();
		this.connection = jdbcconnector.connectDatabase();

		// System.out.println("connexion = " + this.connection.toString());

	}


	public Connection getConnection() {
		return connection;
	}

	public void setConnection(Connection connection) {
		this.connection = connection;
	}

	public boolean checkIfConnectionIsActive() {
		// System.out.println("#checkIfConnectionIsActive# Starting");
		if (connection == null) {
			System.out.println("#checkIfConnectionIsActive# Connection equals null");
			return false;
		}
		ResultSet ping = null;
		try {
			if (connection.isClosed()) {
				System.out.println("#checkIfConnectionIsActive# Connection.isClosed");
				return false;
			}
			ping = connection.createStatement().executeQuery("SELECT 1");
			// System.out.println("#checkIfConnectionIsActive# Ending");
			return ping.next();
		} catch (SQLException sqle) {
			System.out.println("SQLException : "+ sqle);
			return false;
		} finally {
			if (ping != null) {
				try {
					ping.close();
				} catch (Exception e) {
					System.out.println("Exception :" + e);
				}
			}
		}
	}
	
	public List<ProductBase> getCdiscountSic(String listEAN){

		Statement statement = null;
		ResultSet resultSet = null;
		ProductBase sic = null;
		List<ProductBase> lstSIC = new ArrayList<ProductBase>();
		
		String request = ""; 
		 
		try {
			statement = connection.createStatement();
			
			request = "SELECT ITEMID,SIC FROM ITEM_SIC WHERE SIC IN ("+listEAN+");";
			System.out.println("Here the request to get SIC for CDiscount file : " + request);
			
			resultSet = statement.executeQuery(request.toLowerCase());
			
			}catch (SQLException e) {
				e.printStackTrace();
				System.out.println("SQLException : " +e);
				
			}catch (NullPointerException n) {
				n.printStackTrace();
				System.out.println("NullPointerException : " +n);
			}
		
		try {
			
			while(resultSet.next()) {
				
				sic = new ProductBase();
				sic.setItemID(resultSet.getString("ITEMID"));
				sic.setEAN(resultSet.getString("SIC"));

								
				lstSIC.add(sic);
			}
			
		}catch (SQLException e) {
			e.printStackTrace();
			System.out.println("SQLException : " +e);
		}
		
		try {
			statement.close();
			resultSet.close();
		}catch(SQLException e) {
			e.printStackTrace();
			System.out.println("SQLException : " +e);
		}
				
		return lstSIC;
	
	}
	


}