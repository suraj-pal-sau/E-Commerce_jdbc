package com.irfan.jdbc2.jdbc2_transaction_bank;

public class Account
{
	private int id;
	private String name;
	private double balance;
	
	public Account(int id, String name, double balance)
	{
		super();
		this.id = id;
		this.name = name;
		this.balance = balance;
	}

	public int getId()
	{
		return id;
	}

	public String getName()
	{
		return name;
	}

	public double getBalance()
	{
		return balance;
	}

	
	
}
