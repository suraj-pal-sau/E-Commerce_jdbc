package com.irfan.threads5;

///  problem with the synchronized keyword (disadvantage) --> when jvm handling locking and unlocking suppose
/// 	if the thread holds the lock for indefinite time 
/// 	means it is not releasing the lock or get stuck  because of some issues occurred inside synchronized codes
/// 	then other threads waiting to acquire lock will get stuck for a long time (deadlock)  
/// 	issues can be any --> suppose inside synchronized codes, thread trying to access database which is locked now this thread is waiting for 
/// 	the database to be unlocked and this database is not getting unlocked. 

///  solution --> manual locking & unlocking provided by -->  ReentrantLock
/// 		developer can decide when to hold the lock and when to release the lock
/// 

			//java5 -->  ReentrantLock		-->  giving a Flexibility to developer
			//							Whenever they want, put lock and whenever they want, release the lock 
import java.util.concurrent.locks.ReentrantLock;

class BankAccount2
{

	private int balance = 1000;

	ReentrantLock reentrantLock = new ReentrantLock();

	// assume 1000lines
	public void withdraw(int amount)
	{
		System.out.println("* " + Thread.currentThread().getName() + " some other logic --> connecting .....100lines ");

		
		System.out.println("could not acquire the lock --> " + Thread.currentThread().getName() + " Waiting for the lock to be released.... ");
		
		// manually locking
		reentrantLock.lock();
		try
		{
			System.out.println(" acquired the lock --> " + Thread.currentThread().getName()  + " Starting Withdrawal");
			
			if (balance >= amount)
			{
				System.out.println("* " + Thread.currentThread().getName() + " is withdrawing " + amount);
				
				
				try
				{
					System.out.println( Thread.currentThread().getName() + " is going to sleep ");
					Thread.sleep(2000);
				} catch (InterruptedException e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
				balance -= amount;
				System.out.println("* " + Thread.currentThread().getName() + " completed withdrawal. Balance = " + balance);

			} else
			{
				System.out.println("*insufficient balance...");
			}
			
		} finally
		{
			// manually unlocking
			reentrantLock.unlock();
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

public class ReentrantLockExample1
{

	public static void main(String[] args) throws InterruptedException
	{
		BankAccount2 account = new BankAccount2(); // shared object
		System.out.println("* initial balance : " + account.getBalance());

		Customer2 c1 = new Customer2(account, "Azmat is using PhonePe --> ");
		c1.start();

		Customer2 c2 = new Customer2(account, "His bro using Gpay --> ");
		c2.start();

		c1.join();
		c2.join();

		System.out.println("* Final balance : " + account.getBalance()); // getting negative --> data inconsistency

	}

}
