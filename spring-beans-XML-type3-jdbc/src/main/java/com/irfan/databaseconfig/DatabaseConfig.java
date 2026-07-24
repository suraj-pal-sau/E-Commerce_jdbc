package com.irfan.databaseconfig;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.irfan.data.Data;

public class DatabaseConfig
{
	private static Data data = new Data();

	
	private static Connection dbConnection;
	
	public static Connection getConnection() throws SQLException
	{
		if(dbConnection == null || dbConnection.isClosed()) {
			try
			{
				// load the driver
				Class.forName("com.mysql.cj.jdbc.Driver");
				
				//create the connection
				dbConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/practice", data.getUsername(),
						data.getPassword());
				
				System.out.println("\n---- Connected To Database...Successfully ----\n");

				return dbConnection;

			} catch (SQLException | ClassNotFoundException e)
			{
				
				e.printStackTrace();
			}
		}
	
		return dbConnection;

	}

}
