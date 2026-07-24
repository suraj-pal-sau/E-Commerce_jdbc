package com.irfan.threads4;

//every class has a class lock like every object has a object lock
//for static method --> ClassName.class

class BankAccount3
{

	private static int balance = 1000;

	// assume 1000lines
	public static void withdraw(int amount)	//synchronized keyword after or before static keyword both acceptable
	{
		System.out.println(Thread.currentThread().getName() + " some other logic --> connecting .....100lines ");
		
		
		//using class lock
		synchronized (BankAccount3.class)	//synchronized 
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

	}

	public int getBalance()
	{
		return balance;
	}

	public void setBalance(int balance)
	{
		balance = balance;
	}
}

class Customer3 extends Thread
{
	 
	BankAccount3 account;

	public Customer3(BankAccount3 account, String threadName)
	{
		super(threadName);
		this.account = account;
	}

	public void run()
	{
		BankAccount3.withdraw(700);
	}
}

public class StaticMethod
{

	public static void main(String[] args) throws InterruptedException
	{
		BankAccount3 account = new BankAccount3();	//shared object
		System.out.println("initial balance : " + account.getBalance());

		Customer3 c1 = new Customer3(account, "Azmat is using PhonePe --> ");
		c1.start();

		Customer3 c2 = new Customer3(account, "His bro using Gpay --> ");
		c2.start();
		
		c1.join();
		c2.join();
		
		
		System.out.println("Final balance : " + account.getBalance());	//getting negative --> data inconsistency


	}

}
