package com.pricer.main;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;
import java.util.TreeSet;

//import org.apache.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.pricer.model.JDBCConnector;
import com.pricer.product.ProductBase;



public class OperationOnDB {

	static Logger logger =  LogManager.getLogger(OperationOnDB.class);
	
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
	
	public TreeSet<String> lstLinkedItems () {

		Statement st = null;
		ResultSet rs = null;
		String request ;
		TreeSet<String> lstLinkedItems = new TreeSet<String>();

		try {
			st = connection.createStatement();

			request = "select linitemidref from eclink";
			

			rs = st.executeQuery(request.toLowerCase());

			//System.out.println("here is the request : " + request.toLowerCase());

		} catch (SQLException e) {
			e.printStackTrace();
			logger.fatal("SQLException when trying to get linked items : " +e);

		} catch (NullPointerException n) {
			n.printStackTrace();
			logger.fatal("SQLException when trying to get linked items : " +n);
		}

		try {

			while (rs.next()) {
				
				lstLinkedItems.add(rs.getString("linitemidref"));
					
				}							
		}

		catch (SQLException e) {

			e.printStackTrace();
			logger.fatal("SQL Exception when trying to add itemid to a map");

		}

		try {
			st.close();
			rs.close();
			//connection.close();
			
		} catch (SQLException e) {

			e.printStackTrace();
			logger.fatal("SQL Exception when trying to close statement and resultset !");
		}
		


		return lstLinkedItems;

	}
	
	
	public Integer getLastBatchId () {

		Statement st = null;
		ResultSet rs = null;
		String request ;
		int result = 0;

		try {
			st = connection.createStatement();

			request = "SELECT MAX(batchid) AS result FROM ecprintbatchinfo;";
			

			rs = st.executeQuery(request.toLowerCase());

			//System.out.println("here is the request : " + request.toLowerCase());

		} catch (SQLException e) {
			e.printStackTrace();
			logger.fatal("SQLException when trying to get linked items : " +e);

		} catch (NullPointerException n) {
			n.printStackTrace();
			logger.fatal("SQLException when trying to get linked items : " +n);
		}

		try {

			while (rs.next()) {
				
				result = rs.getInt("result");
					
				}							
		}

		catch (SQLException e) {

			e.printStackTrace();
			logger.fatal("SQL Exception when trying to add itemid to a map");

		}

		try {
			st.close();
			rs.close();
			//connection.close();
			
		} catch (SQLException e) {

			e.printStackTrace();
			logger.fatal("SQL Exception when trying to close statement and resultset !");
		}
		


		return result;

	}
	

	public TreeMap<String, String> getlstItemSics() {

		Statement st = null;
		ResultSet rs = null;
		String request ;
		TreeMap<String, String> lstItemSics = new TreeMap<String, String>();

		try {
			st = connection.createStatement();

			request = "select * from item_sic";


			rs = st.executeQuery(request.toLowerCase());

			System.out.println("here is the request : " + request.toLowerCase());

		} catch (SQLException e) {
			e.printStackTrace();
			logger.error("SQL ERROR");

		} catch (NullPointerException n) {
			n.printStackTrace();
			logger.error("SQL ERROR");
		}

		try {

			while (rs.next()) {

				lstItemSics.put(rs.getString("itemid"),rs.getString("sic"));



			}




		}

		catch (SQLException e) {
			logger.error("SQL ERROR");
			e.printStackTrace();

		}

		try {
			st.close();
			rs.close();
			//connection.close();

		} catch (SQLException e) {
			logger.error("SQL ERROR");
			e.printStackTrace();
		}



		return lstItemSics;

	}



	public void deleteItemSIC(StringBuffer requete){


		Statement statement = null;
		ResultSet resultSet = null;

		System.out.println("request for delete  = " + requete);
		try {
			statement = connection.createStatement();
			boolean rs = statement.execute(requete.toString());
			//boolean rs = statement.execute(strRequest);

			if(rs){


				resultSet = statement.getResultSet();

			}else{
				int nbTuples = statement.getUpdateCount();
				logger.info("deleted operation done...." + nbTuples + " Product(s)");

			}


		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.fatal("Unable to execute Request for deleting items");
		}


	}



}