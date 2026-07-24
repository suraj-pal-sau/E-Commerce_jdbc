package com.irfan.practice;

//submit() and execute() only differ in how you submit a task and whether you get a result back. They do not provide thread safety.
///Yes. Race conditions are absolutely possible in the Executor Framework
/// The Executor Framework manages threads, but it does NOT automatically protect shared data.
/**
 * The Executor Framework only answers questions like: 
 * How many threads should be created?
 * Which thread should execute this task? 
 * When should a thread be reused?
 * 
 * It does not prevent two threads from accessing the same variable at the same
 * time.
 */

import java.util.concurrent.*;

class BankAccount
{
	private int balance = 10000;

	public int getBalance()
	{
		return balance;
	}

	public void withdraw(int amount)
	{
		System.out.println("BankAccount.withdraw()............" + Thread.currentThread().getName());
		
		System.out.println(Thread.currentThread().getName() + " is trying for withdrawing ₹" + amount);
		
//		synchronized (this)
//		{
			if (balance >= amount)
			{
				
				balance -= amount;	//critical part
				
				System.out.println(Thread.currentThread().getName() + " is completed withdrawing ₹" + amount);

				System.out.println("Remaining Balance = ₹" + balance);
			} else
			{
				System.out.println(Thread.currentThread().getName() + " --> insufficient balance");
			}
		}

//	}
}

class WithdrawTask implements Runnable{
	BankAccount account;

	public WithdrawTask(BankAccount account)
	{
		
		this.account = account;
	}
	
	public void run() {
		account.withdraw(6000);
	}
	
}
public class RaceConditionExecute
{

	public static void main(String[] args) throws InterruptedException
	{
		BankAccount account = new BankAccount();	//shared account
		System.out.println("initial balance = ₹" + account.getBalance());
		
		ExecutorService executor = Executors.newFixedThreadPool(3);
		
		///without creating separate class
//		executor.execute(() -> account.withdraw(6000));
//		executor.execute(() -> account.withdraw(6000));
		
		/// with separate class for the task withdrawing
		WithdrawTask task = new WithdrawTask(account);
		executor.execute(task);
		executor.execute(task);
		
		executor.shutdown();
		
		
		Thread.sleep(500);
		System.out.println("       Available balance = ₹" + account.getBalance());
		
	}

}
