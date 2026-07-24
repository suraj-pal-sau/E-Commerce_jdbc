package com.irfan.jdbc2.jdbc2_transaction_bank;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import com.irfan.data.Data;

public class AccountService
{
	private Data data = new Data();

	Connection connection;
	Statement statement;

	{
		try
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/practice", "root",
					data.getPassword());
			statement = connection.createStatement();
			connection.setAutoCommit(false);

		} catch (ClassNotFoundException | SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public boolean withdraw(Account sender, double amount) throws SQLException {
		
		int records = 0;
		if(sender.getBalance() >= amount) {
			String query = "update Account SET balance = (balance - " + amount + ") where accountId=" + sender.getId();
			records = statement.executeUpdate(query);
		}else {
			System.out.println("Insufficient Balance");
		}
		
		return records > 0;
	}

	public boolean deposit(Account receiver, double amount) throws SQLException
	{
		
		//creating interruption
		String name=null; name.length();
		String query = "update Account SET balance = (balance + " + amount + ") where accountId=" + receiver.getId();
		int records = statement.executeUpdate(query);
		
		return records > 0;
	}

	public boolean openAccount(Account acc)
	{
		return false;
	}
}
