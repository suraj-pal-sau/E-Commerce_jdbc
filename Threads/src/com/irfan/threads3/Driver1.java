package com.irfan.threads3;

// t1.join(2000) --> existing current thread will wait for 2 sec for t1 thread to complete its run() method

// if t1 is completed run() or not it does not matter, existing current thread will move to forward and will complete its remaining execution

class Cooking extends Thread
{
	public void run()
	{
		System.out.println("Food is being prepared...[" + Thread.currentThread().getName() + "-thread]");

		try
		{
			sleep(3000);
		} catch (InterruptedException e)
		{

			e.printStackTrace();
		}
		System.out.println("Food preparation done...[" + Thread.currentThread().getName() + "-thread]");

	}
}

public class Driver1
{

	public static void main(String[] args) throws InterruptedException
	{

		Thread.currentThread().setName("Waiter"); // main thread name set --> Waiter
		System.out.println("Waiter took the order...[" + Thread.currentThread().getName() + "-thread]");

		Cooking t1 = new Cooking();
		t1.setName("Cook");
		t1.start(); // cooking started

		// example : I will join you at Kolkata / I will join you at 2pm --> person will
		// wait for me at kolkata
		t1.join(); // 'waiter' thread will wait here till 'cook' thread is completed its execution

		System.out.println("Waiter started serving the order...[" + Thread.currentThread().getName() + "-thread]");

	}

}
