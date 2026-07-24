package com.irfan.threads5;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

//tryLock(timeout, timeUnit) --> wait for specified time for a lock --> timed waiting state --> runnable --> running
/// in sleep(1 milliseconds), tryLock(1 milliseconds) --> both thread executing after getting lock but sometime one of thread not getting the lock

class BankAccount3
{
	private int balance = 2000;

	private ReentrantLock lock = new ReentrantLock();

	public void withdraw(int amount)
	{
		System.out.println("BankAccount3.withdraw().....connecting.....  --> " + Thread.currentThread().getName());

		try
		{

			// locking
			if (lock.tryLock(1, TimeUnit.MILLISECONDS))// in 1 milliseconds the thread got the lock without sleep(1 milliseconds)
			{
				System.out.println(
						"***Acquired the Lock --> " + Thread.currentThread().getName() + " <-- Starting Execution");

				// critical part inside try
				try
				{

					if (balance >= amount)
					{
						System.out.println("***" + Thread.currentThread().getName() + " <-- is withdrawing " + amount);
						
						System.out.println("***Sleeping for 1 milliseconds --> " + Thread.currentThread().getName());
						Thread.sleep(1);
						
						balance -= amount;

						System.out.println("***" + Thread.currentThread().getName()
								+ " <-- completed withdrawal. Balance = " + balance);
					}else{
						System.out.println("***" + Thread.currentThread().getName() + " <-- Insufficient Balance");
					}

				} finally
				{ // releasing the lock
					lock.unlock();
				}

			} else
			{
				System.out.println(">>>Not acquired the Lock in time --> " + Thread.currentThread().getName() + " <-- Exiting");
			}

		} catch (InterruptedException e)
		{
			e.printStackTrace();
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
		account.withdraw(1200);
	}

}

public class ReentrantLockExample4
{

	public static void main(String[] args) throws InterruptedException
	{
		BankAccount3 account = new BankAccount3(); // shared object
		System.out.println("initial balance : " + account.getBalance());

		Customer3 c1 = new Customer3(account, "P0 PhonePe");
		c1.start();

		Customer3 c2 = new Customer3(account, "P1 Gpay");
		c2.start();

		c1.join();
		c2.join();

		System.out.println("Final balance : " + account.getBalance());

	}

}
