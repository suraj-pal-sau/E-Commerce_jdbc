package com.irfan.threads7;

import java.util.concurrent.*;

class Bank
{
	private int balance = 3000;

	public int getBalance()
	{
		return balance;
	}

	public void setBalance(int balance)
	{
		this.balance = balance;
	}

	public boolean withdraw(int amount) throws InterruptedException
	{

//		synchronized (this)
//		{
			if (balance >= amount && amount > 0)
			{
				System.out.println(" withdrawing --> " + Thread.currentThread().getName());
				balance -= amount;
				//Thread.sleep(1000);
				System.out.println("Withdrawal completed --> " + Thread.currentThread().getName());

				return true;
			} else
			{
				System.out.println("insufficient balance -> " + Thread.currentThread().getName());
			}
			return false;
//		}

	}
}

class Transaction implements Callable<Boolean>
{
	Bank account;
	
	
	public Transaction(Bank account)
	{
		super();
		this.account = account;
	}


	public Boolean call() throws InterruptedException {
		System.out.println("Transaction initiated by --> " + Thread.currentThread().getName());
		return account.withdraw(1100);
	}
}

public class CallableExample5
{

	public static void main(String[] args) throws InterruptedException, ExecutionException
	{
		Bank account = new Bank();
		System.out.println("initial balance : " + account.getBalance());
		
		
		ExecutorService es = Executors.newFixedThreadPool(6);
		
		//for(int person = 1; person < 4; person++) {
				///using phonepe
			Transaction task = new Transaction(account);
			Future<Boolean> status = es.submit(task);
			if(status.get()) {
				System.out.println("Transaction Successful");
				System.out.println("final balance : " + account.getBalance());
			}else {
				System.out.println("Transaction Failed ");
			}
			
			///using gpay
			Transaction task2 = new Transaction(account);
			Future<Boolean> status2 = es.submit(task2);
			if(status2.get()) {
				System.out.println("Transaction Successful");
				System.out.println("final balance : " + account.getBalance());
			}else {
				System.out.println("Transaction Failed ");
			}
			
			///using card
			Transaction task3 = new Transaction(account);
			Future<Boolean> status3 = es.submit(task3);
			if(status3.get()) {
				System.out.println("Transaction Successful");
				System.out.println("final balance : " + account.getBalance());
			}else {
				System.out.println("Transaction Failed ");
			}
//		}
		
		
		es.shutdown();
		//System.out.println("initial balance : " + account.getBalance());
	}

}
