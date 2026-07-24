package com.irfan.threads5;

//run the program and see execution paused --> another is waiting for a lock to be released but developer forgot to release

///problem with lock() --> what if developers forget to unlock(to release the lock) --> Because now it's not controlled by JVM, controlled by developer.
/// 					then the same problem arises as in the case of synchronized that other thread will wait indefinitely for the lock to be released
/// 					** thread will get stuck waiting for a lock
/// 
/// Solution --> tryLock() and tryLock(waiting_time, time_unit)
/// 			*tryLock() : if it is used -->a thread will try to acquire a lock but the thread will never wait for a lock if the lock is held by another thread --> a thread never move to BLOCKED STATE
/// 						it will go for the other task or will die.
/// 			*tryLock(waiting_time, time_unit) : a thread will wait for specified time only for a lock to be released  -->  a thread will go to BLOCKED STATE for the specified time
/// 									within specified time if it can't get the lock, it will go for the other task or will die.

import java.util.concurrent.locks.ReentrantLock;

class BankAccount
{

	private int balance = 1000;

	private ReentrantLock reentrantLock = new ReentrantLock();

	// assume 1000lines
	public void withdraw(int amount)
	{
		System.out.println(Thread.currentThread().getName() + " some other logic --> connecting .....100lines ");

		
		System.out.println("***could not acquire the lock --> " + Thread.currentThread().getName() + " Waiting ");

		reentrantLock.lock();
		System.out.println(" acquired the lock --> " + Thread.currentThread().getName() + " Starting Withdrawal");

		try
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

		} finally
		{
			///forget to release the lock
			// reentrantLock.unlock();
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

public class ReentrantLockExample2
{

	public static void main(String[] args) throws InterruptedException
	{
		BankAccount account = new BankAccount(); // shared object
		System.out.println("initial balance : " + account.getBalance());

		Customer c1 = new Customer(account, "Azmat is using PhonePe --> ");
		c1.start();

		Customer c2 = new Customer(account, "His bro using Gpay --> ");
		c2.start();

		c1.join();
		c2.join();

		System.out.println("Final balance : " + account.getBalance());

	}

}
