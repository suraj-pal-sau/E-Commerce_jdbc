package com.irfan.jdbc2.jdbc2_transaction_commit_rollback1;

import java.sql.SQLException;

/**
 * Hello world!
 *
 */
public class App
{
	public static void main(String[] args) throws SQLException
	{
		OrderService order = new OrderService();
		order.placeOrder();
	}
}
