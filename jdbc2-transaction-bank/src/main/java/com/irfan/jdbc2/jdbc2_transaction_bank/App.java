package com.irfan.jdbc2.jdbc2_transaction_bank;

import java.sql.SQLException;

/**
 * 
 *
 */
public class App 
{
    public static void main( String[] args ) throws SQLException
    {
    	Account sender = new Account(101, "Irfan", 50000);
    	Account receiver = new Account(102, "Rahul", 35000);
    	
       BankService bankService = new BankService();
       bankService.transferMoney(sender, receiver, 5000);
    }
}
