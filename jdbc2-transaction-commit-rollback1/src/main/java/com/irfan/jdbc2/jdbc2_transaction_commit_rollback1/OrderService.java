package com.irfan.jdbc2.jdbc2_transaction_commit_rollback1;
//Transaction is a single logical unit of work formed by a set of operations. --> ACID
//multiple database updates happens as a unit

//if your program want to interact with database, they will be using JDBC (mechanism to interact with database in java program) 

/** 2. Temporary Storage (WAL)

Before a database permanently changes data, it usually writes information about the change to a special log called the Write-Ahead Log (WAL).

Think of WAL as a rough notebook before writing into your final notebook.*/

///every database has temporary storage before permanent database --> when you store any data it will go to directly permanent database if AUTOCOMMIT=TRUE ---> it is TRUE by default;

///*** set  AUTOCOMMIT=FALSE; --> if it is FALSE then data will go first temporary storage and when you think it is right/complete then COMMIT(then data will be moved to database permanently )
/// ***  giving permission when to store data permanently(using commit) and if you dont want to store data permanently(because of wrong/incomplete data ) then ROLLBACK(it will remove the data from temporary storage)
// set AUTOCOMMIT=TRUE; by default --> it means anything you are storing into database will be stored as permanently 

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import com.irfan.data.Data;

public class OrderService
{
	private Data data = new Data();

	Connection connection;
	Statement statement;

	// iib
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

	public void placeOrder() throws SQLException
	{
		try
		{
			statement = connection.createStatement();

			// autocommit=false --> in sql
			connection.setAutoCommit(false);

			// create order record with status CREATED
			// Confirm the payment
			// Update the orders tables with PAID

			/// 1
			String createOrderSql = "INSERT INTO ORDERS(id, item,status) VALUES(1,'IPHONE18', 'CREATED')";

			int records = statement.executeUpdate(createOrderSql);

			if (records > 0)
			{
				System.out.println("order record created");
			} else
			{
				System.out.println("unable to create record");
			}

			/// 2
			String createPaymentSql = "INSERT INTO Payments VALUES(1,'PR1234', 'INIT')";

			records = statement.executeUpdate(createPaymentSql);
			
			if (records > 0)
			{
				System.out.println("payment record created");
			} else
			{
				System.out.println("unable to create payment record");
			}

			// user did not have enough balance
			String updatePayment = "update payments set status='FAILED' where id=1";

			
			String name = null;	/// suppose any interruption occurred --> it will goto catch block from here
			name.length();
			
			records = statement.executeUpdate(updatePayment);
			if (records > 0) {
				System.out.println(" Payment record updated.");
			} else {
				System.err.println(" unable to update payment record");
			}
			
			System.out.println("doing commit");
			connection.commit();
		} catch (Exception e)
		{
			e.printStackTrace();
			System.out.println("Exception occured --> doing rollback");
			connection.rollback();
		}

	}
}
