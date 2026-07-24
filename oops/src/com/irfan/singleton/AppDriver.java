package com.irfan.singleton;

import java.sql.Connection;
import java.sql.SQLException;

public class AppDriver {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
            DatabaseConnection db1 = DatabaseConnection.getInstance();
            Connection conn1 = db1.getConnection();

            DatabaseConnection db2 = DatabaseConnection.getInstance();
            Connection conn2 = db2.getConnection();

            // Both conn1 and conn2 point to the same connection
            System.out.println(conn1 == conn2); // true
        } catch (SQLException e) {
            e.printStackTrace();
        }
	}

}
