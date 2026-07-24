package com.irfan.practice;

import java.util.concurrent.*;


/**
 * One thread ≠ always synchronous Multiple threads ≠ always asynchronous
 *** 
 * The deciding factor is:
 * 
 * Does someone wait for another task to finish? Yes → Synchronous No →
 * Asynchronous
 */

class BankAcc
{

	private int balance = 10000;

	public int getBalance()
	{
		return balance;
	}

	public boolean withdraw(int amount)
	{
		System.out.println("BankAccount.withdraw()............" + Thread.currentThread().getName());

		System.out.println(Thread.currentThread().getName() + " is trying for withdrawing ₹" + amount);

//		synchronized (this)
//		{
			if (balance >= amount)
			{

				balance -= amount; // critical part

				System.out.println(Thread.currentThread().getName() + " is completed withdrawing ₹" + amount);

				System.out.println("Remaining Balance = ₹" + balance);
				
				return true;
			} else
			{
				System.out.println(Thread.currentThread().getName() + " --> insufficient balance");
			}
//		}
		
		return false;

	}

}

//class Withdrawal implements Callable<Boolean>{
//	private BankAcc task;
//	
//	public Withdrawal(BankAcc task)
//	{
//		super();
//		this.task = task;
//	}
//
//	public Boolean call() {
//		if(task.withdraw(6000)) return true;
//		return false;
//	}
//}

public class RaceConditionSubmit
{

	public static void main(String[] args) throws InterruptedException, ExecutionException
	{
		ExecutorService executr = Executors.newFixedThreadPool(2);
		
		BankAcc account = new BankAcc();
		
		System.out.println("Balance : " + account.getBalance());
		
//		Withdrawal task = new Withdrawal(account);
		
		Future<Boolean> phonePe = executr.submit(() -> account.withdraw(6000));
		
		Future<Boolean> gPay = executr.submit(() -> account.withdraw(5000));
		
		System.out.println("\nWaiting for transactions...\n");

        boolean phonePeStatus = phonePe.get();

        boolean gPayStatus = gPay.get();

        System.out.println("\n-----------------------");

        System.out.println("PhonePe Success : " + phonePeStatus);

        System.out.println("GPay Success    : " + gPayStatus);

        System.out.println("Final Balance   : ₹" + account.getBalance());

        executr.shutdown();

	}

}
