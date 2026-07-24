package com.irfan.threads5;

//sleep(time) --> thread will not release the LOCK and go to sleep i.e TIMED WAITING STATE
	//disadvantage of using sleep() --> another thread not got the chance for executing since current thread did not release the lock and went to sleep

///solution --> wait() -->  thread will release the LOCK and pause  i.e WAITING STATE
//			 wait(time) -->  thread will release the LOCK and pause for specified time i.e TIMED WAITING STATE	

/// join() --> WAITING STATE,  join(time) --> TIMED WAITING STATE

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

class Bank
{
	private int balance = 1500;
	private ReentrantLock lock = new ReentrantLock();

	public void doTransaction(int amount) 
	{

		System.out.println("Bank.doTransaction()....connecting to server  --> " + Thread.currentThread().getName());
		// System.out.println("Bank.doTransaction()....connected successfully --> " +
		// Thread.currentThread().getName());

		try
		{
			if (lock.tryLock(2000, TimeUnit.MICROSECONDS))
			{
				System.out.println(
						"***Lock is available --> " + Thread.currentThread().getName() + " <-- Starting Execution");

				try
				{

					if (balance >= amount)
					{
						System.out.println(
								"***" + Thread.currentThread().getName() + " <-- is starting Transaction " + amount);

						 System.out.println("***Sleeping for 1 milliseconds --> " +
						 Thread.currentThread().getName());
						 try
						{
							Thread.sleep(1000);
						} catch (InterruptedException e)
						{
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						 
						balance -= amount;

						System.out.println("***" + Thread.currentThread().getName()
								+ " <-- completed Transaction, Balance = " + balance);
					} else
					{
						System.out.println("***" + Thread.currentThread().getName() + " <-- Insufficient Balance");
					}

				} finally
				{
					lock.unlock();
				}
			} else
			{

				System.out.println("^^^Not acquired the Lock in time --> " + Thread.currentThread().getName());

			}
		} catch (InterruptedException e)
		{
			// TODO Auto-generated catch block
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

class User extends Thread
{
	Bank account;

	public void run()
	{
		account.doTransaction(600);
	}

	public User(Bank account, String threadName)
	{
		super(threadName);
		this.account = account;
	}

}

public class SleepExample
{

	public static void main(String[] args) throws InterruptedException
	{
		Bank account = new Bank();
		System.out.println("Initial Balance :" + account.getBalance());

		User user1 = new User(account, "Debit-Card");
		user1.start();

		User user2 = new User(account, "PhonePe");
		user2.start();

		User user3 = new User(account, "GPay");
		user3.start();

		// join() --> join(0) --> waiting for forever (until thread completes execution)
		user1.join(); /// main thread Waits for thread --> user1 to die --> 'main' thread will go to
						/// waiting state
		user2.join(); // main thread Waits for thread --> user2 to die.
		user3.join(); // main thread Waits for thread --> user3 to die.

		System.out.println("Final Balance :" + account.getBalance());
	}

}
