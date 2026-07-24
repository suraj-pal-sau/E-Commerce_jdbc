package com.irfan.threads5;

//tryLock() --> no waiting if lock not available
/// there is no problem even if the developer forget to release the lock --> see finally block and run the program
/// but if one thread got the lock then it will have the lock forever until you release the lock
/// best practice --> unlock
/// 
/// problem is solved for a thread which don't get the lock, the thread will not wait(not getting stuck for a lock) --> solution
/// but if one thread got the lock then  that thread must release the lock after completing its execution
///  so if you don't unlock, then that thread will never release the lock 

import java.util.concurrent.locks.ReentrantLock;

class BankAccount1
{

	private int balance = 1000;

	private ReentrantLock reentrantLock = new ReentrantLock();

	// assume 1000lines
	public void withdraw(int amount)
	{
		System.out.println(Thread.currentThread().getName() + " some other logic --> connecting .....100lines ");

		

		if(reentrantLock.tryLock()) {
			
			System.out.println("***Acquired the lock --> " + Thread.currentThread().getName() + " Starting Withdrawal");

			try
			{

				if (balance >= amount)
				{
					System.out.println(Thread.currentThread().getName() + " is withdrawing " + amount);
					balance -= amount;
					System.out
							.println(Thread.currentThread().getName() + " completed withdrawal. Balance = " + balance);

				} else
				{
					System.out.println("insufficient balance...");
				}

			} finally
			{
				/// forget to release the lock
				// reentrantLock.unlock();
			}
		}else {
			System.out.println("***could not acquire the lock --> " + Thread.currentThread().getName() + " Exiting ");

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


public class ReentrantLockExample3
{

	public static void main(String[] args) throws InterruptedException
	{
		BankAccount1 account = new BankAccount1(); // shared object
		System.out.println("initial balance : " + account.getBalance());

		Customer1 c1 = new Customer1(account, "Azmat is using PhonePe --> ");
		c1.start();

		Customer1 c2 = new Customer1(account, "His bro using Gpay --> ");
		c2.start();

		c1.join();
		c2.join();

		System.out.println("Final balance : " + account.getBalance());
	}

}
