package com.pricer.main;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.pricer.model.JDBCConnector;



public class OperationOnDB {
	
	
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
	


}