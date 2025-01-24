/*
 * and open the template in the editor.
 */
package com.fmsinvoicefeed.utils;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.log4j.Logger;

import com.fmsinvoicefeed.logger.LoggerHelper;

/**
 *
 * @author Shanker Pasula
 */
public class DatabaseConnection extends Base{
	final static Logger logger = LoggerHelper.getLogger(DatabaseConnection.class);

    private static DatabaseConnection instance;
    private static Connection connection;
    private static Statement statement;
    private static ResultSet rs;
    private static String dbServer;
    private static String dbName;

    private DatabaseConnection(String dbServer, String dbName) throws Exception {
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

    public static Connection getConnection() {
        return connection;
    }

    public static DatabaseConnection getInstance() throws Exception {
        if (instance == null) {
            instance = new DatabaseConnection(dbServerQA, dbNameQA);
        } else if (instance.getConnection().isClosed()) {
            instance = new DatabaseConnection(dbServerQA, dbNameQA);
        }
        return instance;
    }
}