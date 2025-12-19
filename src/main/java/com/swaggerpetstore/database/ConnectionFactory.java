package com.swaggerpetstore.database;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class ConnectionFactory {

	private static ThreadLocal<Map<String, Connection>> connectionMap = ThreadLocal.withInitial(HashMap::new);

	// Load connection details from the properties file
	private static Properties dbProperties;
	private static String environment;

	static {
		try {
			environment = System.getProperty("env");
			String configFile = "application-" + environment + ".properties";
			dbProperties = loadDbProperties(configFile);
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException("Error loading database properties", e);
		}
	}

	// Private method to create a connection based on dbName
	private static Connection createConnection(String dbName) throws SQLException {
		String url = dbProperties.getProperty(dbName + ".url");
		String username = dbProperties.getProperty(dbName + ".username");
		String password = dbProperties.getProperty(dbName + ".password");

		return DriverManager.getConnection(url, username, password);
	}

	// Factory method to get a connection based on the database name
	public static Connection getConnection(String dbName) {
		Map<String, Connection> connections = connectionMap.get();

		// If the connection for the given dbName doesn't exist, create it
		if (!connections.containsKey(dbName)) {
			try {
				Connection conn = createConnection(dbName);
				connections.put(dbName, conn); // Store the connection in the map
			} catch (SQLException e) {
				e.printStackTrace();
				throw new RuntimeException("Error creating connection for " + dbName, e);
			}
		}

		return connections.get(dbName); // Return the connection for the dbName
	}

	// Helper method to load the database properties from the file
	private static Properties loadDbProperties(String fileName) throws IOException {
		Properties properties = new Properties();
		try (InputStream input = ConnectionFactory.class.getClassLoader().getResourceAsStream(fileName)) {
			if (input == null) {
				throw new IOException("Unable to find " + fileName);
			}
			properties.load(input);
		}
		return properties;
	}

	/**
	 * Method to close all connections (called when done with DB operations) This
	 * method iterates through all connections stored in the ThreadLocal map This
	 * method we can use it in afterSuite Method to clean up the connection
	 * 
	 */

	public static void closeAllConnections() {
		Map<String, Connection> connections = connectionMap.get();
		for (Connection conn : connections.values()) {
			try {
				if (conn != null && !conn.isClosed()) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		// Clean up ThreadLocal storage
		connectionMap.remove();
	}

	// Refresh the DB connection for the given dbName
	public static void refreshConnection(String dbName) {
		Map<String, Connection> connections = connectionMap.get();
		try {
			if (connections.containsKey(dbName)) {
				Connection oldConn = connections.get(dbName);
				if (oldConn != null && !oldConn.isClosed()) {
					oldConn.close();
				}
				connections.remove(dbName);
			}

			Connection newConn = createConnection(dbName);
			connections.put(dbName, newConn);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("Failed to refresh connection for DB: " + dbName, e);
		}
	}
}