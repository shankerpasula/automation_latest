package com.fmsinvoicefeed.utils;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import org.apache.log4j.Logger;

import com.fmsinvoicefeed.logger.LoggerHelper;

import net.bytebuddy.asm.Advice.Return;

public class DBUtils {
	final static Logger logger = LoggerHelper.getLogger(DBUtils.class);
	public static Connection connection;
	public static Statement statement;
	public static ResultSet rs;
	public static String dbServer;
	public static String dbName;
	public static String customerCode;
	public static String connectionString;
	/**
	 * This method is used to create database connection
	 * 
	 * @author Shanker Pasula
	 * @version 1.0
	 * @since 10-19-2019
	 * @param wetwetw
	 */
	public static void ConnectDataBase(String dbServer, String dbName) throws Exception {

		String path = System.getProperty("java.library.path");
		path = "./src/main/resources/drivers" + ";" + path;
		System.setProperty("java.library.path", path);
		try {
			final Field sysPathsField = ClassLoader.class.getDeclaredField("sys_paths");
			sysPathsField.setAccessible(true);
			sysPathsField.set(null, null);
		} catch (Exception ex) {
			throw new RuntimeException(ex);
		}
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		try {
			logger.info("Connecting to DataBase..." + dbServer + ":" + dbName);
			connection = DriverManager.getConnection("jdbc:sqlserver://" + dbServer + ":1433;databaseName=" + dbName + ";integratedSecurity=true;");
			if (connection != null) {
				logger.info("Successfully Connected...");
			}
		} catch (Exception e) {
			throw new Exception("Failed to Connect to DataBase..." + e.getMessage());
		}
	}

	/**
	 * This method is used to get the Data From Database
	 * 
	 * @author Shanker Pasula
	 * @version 1.0
	 * @throws Exception
	 * @since 10-19-2019
	 *@Return 
	 */
	
	public static void ConnectDataBase(Connection conn) throws Exception {
		
		try {
			connection = conn;
			if (connection != null) {
				//logger.info("Successfully Connected...");
			}
		} catch (Exception e) {
			throw new Exception("Failed to Connect to DataBase..." + e.getMessage());
		}
	}
	
	public ArrayList<ArrayList<String>> getDataFromDatabase(String query) {
		ArrayList<ArrayList<String>> al = new ArrayList<ArrayList<String>>();
		//logger.info("Executing Query ..............");
		try {
			statement = connection.createStatement();
			//logger.info(query);
			rs = statement.executeQuery(query);
			ResultSetMetaData rsmd = rs.getMetaData();
			int colCount = rsmd.getColumnCount();
			ArrayList<String> l = new ArrayList<String>();
			for (int i = 1; i <= colCount; i++) {
				String colName = rsmd.getColumnName(i);
				l.add(colName);
			}
			al.add(l);
			int columnNumber = 0;
			while (rs.next()) {
				l = new ArrayList<String>();
				while (columnNumber != colCount)
					l.add(rs.getString(al.get(0).get(columnNumber++)));
				al.add(l);
				columnNumber = 0;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		for (int i = 0; i < al.size(); i++)
			//logger.info(al.get(i));
			al.get(i).trimToSize();
		return al;
	}
	
	/**
	 * This method is used to get the Data From Database with query based on invoiceNo
	 * 
	 * @author Shanker Pasula
	 * @version 1.0
	 * @throws Exception
	 * @since 10-19-2019
	 *@Return 
	 */
	public ArrayList<ArrayList<String>> getDataFromDatabase(String query , String invoiceNo ,String vehicleNumber) {
		ArrayList<ArrayList<String>> al = new ArrayList<ArrayList<String>>();
		logger.info("Executing Query ..............");
		try {
			statement = connection.createStatement();
			//logger.info(query);
			rs = statement.executeQuery(query);
			ResultSetMetaData rsmd = rs.getMetaData();
			int colCount = rsmd.getColumnCount();
			ArrayList<String> l = new ArrayList<String>();
			for (int i = 1; i <= colCount; i++) {
				String colName = rsmd.getColumnName(i);
				l.add(colName);
			}
			al.add(l);
			int columnNumber = 0;
			while (rs.next()) {
				l = new ArrayList<String>();
				while (columnNumber != colCount)
					l.add(rs.getString(al.get(0).get(columnNumber++)));
				al.add(l);
				columnNumber = 0;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		for (int i = 0; i < al.size(); i++)
			//logger.info(al.get(i));
			al.get(i).trimToSize();
		return al;
	}
	
	
	/**
	 * This method is used to close the database connection
	 * 
	 * @author Shanker Pasula
	 * @version 1.0
	 * @throws Exception
	 * @since 10-19-2019
	 */
	public static void closeDatabase() throws Exception {
		if (connection != null) {
			try {
				//logger.info("Closing Database Connection...");
				connection.close();
				logger.info("Successfully Closed Database Connection...");
			} catch (SQLException e) {
				throw new Exception("Failed to Close the DataBase..." + e.getMessage());
			}
		}
	}
	
	/**
	 * This method is used to get the columnName From the record
	 * 
	 * @author Shanker Pasula
	 * @version 1.0
	 * @since 10-19-2019
	 */
	public static String getColumnName(ArrayList<String> columnList, int columnIndex) {
		String columnVal = null;
		for (int i = 0; i <= columnList.size() - 1; i++) {
			if (i==columnIndex) {
				columnVal = columnList.get(i);
				break;
			}
		}
		return columnVal;
	}
	
	/**
	 * This method is used to get Row count From DataTable
	 * 
	 * @author Shanker Pasula
	 * @version 1.0
	 * @since 02-28-2020
	 */
	
	public int[] getRowcountFromDataTable(String customerCode ,String sourceQuery, String targetQuery ) {
		ArrayList<ArrayList<String>> oldSource = getDataFromDatabase(sourceQuery);
		ArrayList<ArrayList<String>> newSource = getDataFromDatabase(targetQuery);
		int oldSourceRowCount = oldSource.size()-1;
		int newSourceRowCount = newSource.size()-1;
		//logger.info("RawDate RowCount Found in Old & New Sources :" + oldSourceRowCount + "|" + newSourceRowCount);
		return new int[] {oldSourceRowCount,newSourceRowCount};
		
	}
}