package com.epam.tat.selenium.source.reader;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.epam.tat.selenium.entities.ConfigData;

public class DBReader implements Reader {

	public static Connection getConnection() {
		try {
			Class.forName("org.hsqldb.jdbcDriver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		Connection connection = null;
		try {
			connection = DriverManager.getConnection(
					"jdbc:hsqldb:file:dbpath/dbname", "SA", "");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return connection;
	}

	public ConfigData read() {

		return read(getConnection());
	}

	public static void dropTable(Connection connection) {
		try {
			Statement statement = connection.createStatement();
			String query = "DROP TABLE driverProp IF EXISTS";
			try {
				statement.executeUpdate(query);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
	
	public static void createTable(Connection connection) {
		dropTable(connection);
		try {
			Statement statement = connection.createStatement();
			String query = "CREATE TABLE driverProp (id int IDENTITY , browser VARCHAR(25) , grid_url VARCHAR(25), url VARCHAR(25))";
			try {
				statement.executeUpdate(query);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public static void insertData(Connection connection) {
		try {
			Statement statement = connection.createStatement();
			String query = "INSERT INTO driverProp (browser, grid_url, url) VALUES('google_chrome', 'http://localhost:4444/wd/hub', 'https://mail.ru/')";
			statement.executeUpdate(query);
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static ConfigData read(Connection connection) {
		try {
			Statement statement = connection.createStatement();
			String query = "SELECT browser, grid_url, url FROM driverProp";
			ResultSet resultSet = statement.executeQuery(query);
			statement.close();
			while (resultSet.next()) {
				 return new ConfigData(resultSet.getString(1), resultSet.getString(2), resultSet.getString(3));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;

	}

	public static void closeConnection(Connection connection) {
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
			System.exit(1);
		}
	}

}
