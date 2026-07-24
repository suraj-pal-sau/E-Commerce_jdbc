package com.irfan.jdbc3.jdbc3_prepare_statement;

//batch systems --> suppose you have 10k products detail you want to add in database --> batch the 1000 products in one batch --> then you need 10 batches to execute 
// without batching, 10k database hits, every time insert... query will be compiled(at database level) 

//suppose 100 people --> 100 car --> expensive(100 drivers required) time consuming --> performance slow
//batch --> 1 bus = 25people --> 1 batch, then you need 4 batch ===> 25people X 4 
//purpose --> 25 people will go at the same time --> 1 database hit --> 1 time query get executed

///prepareStatement is faster than Statement --> statement compiled every time
/// prepareStatement compiled once --> read javaDoc 

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.irfan.data.Data;

public class PrepareStatementExample
{
	private Data data = new Data();

	Connection connection;
	PreparedStatement ps;

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
		} // dynamic class loading

	}

	public void updatePayment(String status, int id) throws ClassNotFoundException, SQLException
	{

		String query = "update payments set status=? where id=?";

		PreparedStatement ps = connection.prepareStatement(query);

		ps.setString(1, status);
		ps.setInt(2, id);

		int records = ps.executeUpdate();
		if (records > 0)
		{
			System.out.println("records updated : " + records);

		} else
		{
			System.err.println("unable to update the records");
		}

	}

	public void insertRecords() throws SQLException
	{

		String iquery = "Insert into payments values(?, ?, ?)";

		PreparedStatement ps = connection.prepareStatement(iquery);
		connection.setAutoCommit(false);
		for (int i = 1; i < 100; i++)
		{
			ps.setInt(1, i);
			ps.setString(2, "REFID" + i);
			ps.setString(3, "INIT");

			ps.addBatch();
		}
		System.out.println("executing query....");
		ps.executeBatch(); // in one database hit --> 99 row inserted (batch of 99 queries)
		connection.commit();
	}

	public void insertRecordsInBatch() throws SQLException
	{

		String iquery = "Insert into payments values(?, ?, ?)";

		PreparedStatement ps = connection.prepareStatement(iquery);
		connection.setAutoCommit(false);
		int batchSize = 10;
		for (int i = 1; i < 100; i++)
		{
			ps.setInt(1, i);
			ps.setString(2, "REFID" + i);
			ps.setString(3, "INIT");

			ps.addBatch(); // adding sql commands in a batch

			if (i % batchSize == 0)
			{ // it means batch of 10 SQL commands/queries and
				System.out.println("Executing 10 Queries............." + i);
				ps.executeBatch(); // --> after 10 queries added, execute this batch --> it will execute a batch of 10 queries 9 times--> 10*9=90
									// 
				ps.clearBatch(); /// Empties this Statement object's current list of SQL commands.
			}

		}
		System.out.println("executing remaining query....91 -99");
		// comment it below code you will see in table 90 records are added only
		ps.executeBatch(); // it will execute remaining 91 -99
		connection.commit();
	}
}
