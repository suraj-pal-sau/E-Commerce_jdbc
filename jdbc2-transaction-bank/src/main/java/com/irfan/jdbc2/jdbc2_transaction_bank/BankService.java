package com.irfan.jdbc2.jdbc2_transaction_bank;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import com.irfan.data.Data;

public class BankService
{
	private Data data = new Data();

	AccountService accountService = new AccountService();

	Connection connection;
	Statement statement;

	{
		try
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/practice", "root",
					data.getPassword());

		} catch (ClassNotFoundException | SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void transferMoney(Account sender, Account receiver, double amount) throws SQLException
	{

		try
		{
			connection.setAutoCommit(false);

			statement = connection.createStatement();

			// 1) withdraw
			boolean withdrawStatus = accountService.withdraw(sender, amount);
			if (withdrawStatus)
			{
				System.out.println("withdraw successful");
			} else
			{
				System.out.println("withdraw failed");
			}

			// 2) deposit
			boolean depositStatus = accountService.deposit(receiver, amount);
			if (withdrawStatus && depositStatus)
			{
				System.out.println("deposit successful");
			} else
			{
				System.out.println("deposit failed");
				throw new Exception();
			}

			System.out.println("Transaction successful...");
			connection.commit();

		} catch (Exception e)
		{
			e.printStackTrace();
			System.out.println("doing rollback.....");
			connection.rollback();
		}

	}

}
