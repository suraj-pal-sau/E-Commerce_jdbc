package com.irfan.threads5;

///wait() is in --> Object class     // sleep()  is in --> Thread class
// wait() --> release the lock, so another thread will get the lock and will get the chance of executing 
// wait() --> it needs notify() or notifyAll() to wake the waiting thread.

/******** change wait() to wait(timeout)  --> see the change written below */
///*** wait() --> thread is waiting for indefinite time --> run the program -> the program will never complete its execution
///*** wait(timeout) --> using wait(timeout) without any notify() or notifyAll() can indeed lead to race conditions and data inconsistencies.
/// **reason for race conditions/data inconsistency --> 1) Threads wake up independently after their timeout expires.
///								2) Since no coordination (notify) is happening, multiple threads may resume at nearly the same time.

// exception thrown --> if wait() is used inside anywhere instead inside of the synchronized method or block
/// *IllegalMonitorStateException* -->  if the current thread is not the owner of the object's monitor lock.

//Why wait() Always need to call from synchronized block?  --> Because wait need to release the object lock, and synchronize will make sure that
//it'll have object lock.



class Bank1
{
	private int balance = 1500;

	public void doTransaction(int amount)
	{
		System.out.println("Bank.doTransaction()....connecting to server  --> " + Thread.currentThread().getName());

		// System.out.println("Bank.doTransaction()....connected successfully --> " +
		// Thread.currentThread().getName());

		synchronized (this)
		{
			System.out.println(
					"***Lock is available --> " + Thread.currentThread().getName() + " <-- Starting Execution");

			if (balance >= amount)
			{
				System.out.println("***" + Thread.currentThread().getName() + " <-- is starting Transaction " + amount);

				try
				{
					System.out.println( Thread.currentThread().getName() + " <-- Thread is going to wait...  ");
					wait(); // releases the monitor lock and waits until notified
					System.out.println( Thread.currentThread().getName() + " <-- Thread resumed after notification!");
				} catch (InterruptedException e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				balance -= amount;

				System.out.println(
						"***" + Thread.currentThread().getName() + " <-- completed Transaction, Balance = " + balance);
			} else
			{
				System.out.println("***" + Thread.currentThread().getName() + " <-- Insufficient Balance");
			}
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

class User1 extends Thread
{
	Bank1 account;

	public void run()
	{
		account.doTransaction(600);
	}

	public User1(Bank1 account, String threadName)
	{
		super(threadName);
		this.account = account;
	}

}

public class WaitExample
{

	public static void main(String[] args) throws InterruptedException
	{

		Bank1 account = new Bank1();
		System.out.println("Initial Balance :" + account.getBalance());

		User1 user1 = new User1(account, "Debit-Card");
		user1.start();

		User1 user2 = new User1(account, "PhonePe");
		user2.start();

		User1 user3 = new User1(account, "GPay");
		user3.start();

		// join() --> join(0) --> waiting for forever (until thread completes execution)
		user1.join(); /// main thread Waits for thread --> user1 to die --> 'main' thread will go to
						/// waiting state
		user2.join(); // main thread Waits for thread --> user2 to die.
		user3.join(); // main thread Waits for thread --> user3 to die.

		System.out.println("Final Balance :" + account.getBalance());

	}

}
