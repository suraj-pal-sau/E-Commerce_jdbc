package com.irfan.threads4;

// advantage of synchronized : jvm handling locking and unlocking, you don't have to worry about it --> acquiring lock and releasing the lock 

class BankAccount2
{

	private int balance = 1000;

	// assume 1000lines
	public void withdraw(int amount)
	{
		// the below code should be accessible by multiple threads 
		// otherwise --> it will slow down --> below code is not critical code
		System.out.println(Thread.currentThread().getName() + " some other logic --> connecting .....100lines ");
		//now the above code is multithreaded
		
		
		//synchronizing only critical part of the method 
		synchronized (this)	//synchronized current object
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
		this.balance = balance;
	}
}

class Customer2 extends Thread
{
	BankAccount2 account;

	public Customer2(BankAccount2 account, String threadName)
	{
		super(threadName);
		this.account = account;
	}

	public void run()
	{
		account.withdraw(700);
	}
}

public class SynchronizedBlock
{

	public static void main(String[] args) throws InterruptedException
	{
		BankAccount2 account = new BankAccount2();	//shared object
		System.out.println("initial balance : " + account.getBalance());

		Customer2 c1 = new Customer2(account, "Azmat is using PhonePe --> ");
		c1.start();

		Customer2 c2 = new Customer2(account, "His bro using Gpay --> ");
		c2.start();
		
		c1.join();
		c2.join();
		
		
		System.out.println("Final balance : " + account.getBalance());	//getting negative --> data inconsistency

	}

}
