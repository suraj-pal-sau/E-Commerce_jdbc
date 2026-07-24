package com.irfan.jdbc3.jdbc3_prepare_statement;

import java.sql.SQLException;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws ClassNotFoundException, SQLException
    {
        PrepareStatementExample example = new PrepareStatementExample();
       // example.updatePayment("Block", 1);
        //example.insertRecords();
        example.insertRecordsInBatch();
    }
}
