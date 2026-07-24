package com.irfan.practice;
///Two transfers are happening simultaneously:
///	Thread-1: PhonePe transfers ₹500 from Irfan → Rahul.
///	Thread-2: GPay transfers ₹300 from Rahul → Irfan.

///There are two shared resources:
///		accountLock → Protects account balances.
///		transactionLock → Protects transaction history.

class MyThread1 extends Thread
{

	public void run()
	{

		synchronized (Bank.accountLock)
		{

			System.out.println(getName() + " locked AccountLock");

			try
			{
				Thread.sleep(1000);
			} catch (InterruptedException e)
			{
				e.printStackTrace();
			}

			System.out.println(getName() + " waiting for Transaction Lock");

			synchronized (Bank.transactionLock)
			{

				System.out.println(getName() + " Transfer Successful");
			}
		}
	}
}

class MyThread2 extends Thread
{

	public void run()
	{
		synchronized (Bank.transactionLock)
		{

			System.out.println(getName() + " got Transaction Lock");

			try
			{
				Thread.sleep(1000);
			} catch (InterruptedException e)
			{
				e.printStackTrace();
			}

			System.out.println(getName() + " waiting for Account Lock");

			synchronized (Bank.accountLock)
			{

				System.out.println(getName() + " Transfer Successful");
			}
		}
	}
}

class Bank
{
	public static final Object accountLock = new Object();
	public static final Object transactionLock = new Object();
}

public class DeadLockSituation2
{

	public static void main(String[] args)
	{
		MyThread1 t1 = new MyThread1();
		t1.setName("PhonePe");

		MyThread2 t2 = new MyThread2();
		t2.setName("GPay");

		t1.start();
		t2.start();

	}

}
