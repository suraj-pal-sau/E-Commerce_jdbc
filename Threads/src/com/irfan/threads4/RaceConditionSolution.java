package com.irfan.threads4;

/// synchronized internal working --> every object/class has one and only lock 
/// 		--> when a thread trying to enter a synchronized block/method, it must have to acquire object/class lock
/// 		--> if a thread get the lock then it will executes the synchronized code
///     --> and the rest threads will go to BLOCKED STATE means they will wait to acquire the lock
/// 		--> if a thread holds the lock, the rest threads will wait until the lock is released

//synchronized --> the withdraw method --> slow down the performance
/// problem --> synchronized entire method --> entire method is single threaded --> performance slow down
// solution --> synchronized block --> synchronized only critical piece of code not the entire method

class BankAccount1
{

	private int balance = 1000;

	// it will slow down the performance --> single threaded
	public synchronized void withdraw(int amount)
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

class Customer1 extends Thread
{
	BankAccount1 account;

	public Customer1(BankAccount1 account, String threadName)
	{
		super(threadName);
		this.account = account;
	}

	public void run()
	{
		account.withdraw(700);
	}
}

public class RaceConditionSolution
{

	public static void main(String[] args) throws InterruptedException
	{
		BankAccount1 account = new BankAccount1(); // shared object
		System.out.println("initial balance : " + account.getBalance());

		Customer1 c1 = new Customer1(account, "Azmat is using PhonePe");
		c1.start();

		Customer1 c2 = new Customer1(account, "Azmat's bro using Gpay");
		c2.start();

		c1.join();
		c2.join();

		System.out.println("Final balance : " + account.getBalance()); // getting negative --> data inconsistency

	}

}
