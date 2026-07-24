package com.irfan.threads4;

class BankAccount
{

	private int balance = 1000;

	public void withdraw(int amount)
	{
		if (balance >= amount)
		{
			System.out.println(Thread.currentThread().getName() + " is withdrawing " + amount);
			balance -= amount;
			System.out.println(Thread.currentThread().getName() + " completed withdrawal. Balance = " + balance);

		} else
		{
			System.out.println("insufficient balance...");
		}
	}

	public int getBalance()
	{
		return balance;
	}

	public void setBalance(int balance)
	{
		this.balance = balance;
	}
}

class Customer extends Thread
{
	BankAccount account;
	

	public Customer(BankAccount account, String threadName)
	{
		super(threadName);
		this.account = account;
	}

	public void run()
	{
		account.withdraw(700);
	}
}

public class RaceCondition1
{

	public static void main(String[] args) throws InterruptedException
	{
		BankAccount account = new BankAccount();	//shared object
		System.out.println("initial balance : " + account.getBalance());

		Customer c1 = new Customer(account, "Azmat is using PhonePe");
		c1.start();

		Customer c2 = new Customer(account, "Azmat's bro using Gpay");
		c2.start();
		
		c1.join();
		c2.join();
		
		
		System.out.println("Final balance : " + account.getBalance());	//getting negative --> data inconsistency

	}

}
