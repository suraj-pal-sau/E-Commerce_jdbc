package com.irfan.threads3;

class MyThread1 extends Thread
{
	Task1 task;

	public MyThread1(Task1 task)
	{
		this.task = task;
	}

	public void run()
	{
		System.out.println("Setting up the payment pathways... --> " + Thread.currentThread().getName());
		task.doTask();
	}
}

class Task1
{

	public synchronized void doTask()
	{
		System.out.println("Starting payment process... --> " + Thread.currentThread().getName());
		Payment1 p = new Payment1();
		p.doPaymet();
	}
}

class Payment1
{

	// race condition --> bank will loose money
	// use --> synchronized for allowing one thread to execute completely at a time 
	// and then other thread will be allowed to execute
	public synchronized void doPaymet()
	{
		System.out.println("Payment.doPaymet()....doing payment....--> " + Thread.currentThread().getName());
	}
}

public class RaceConditionSolution
{

	public static void main(String[] args)
	{
		// shared resources
		Task1 task = new Task1();

		// multiple threads trying for accessing and modifying the same resources
		// concurrently
		MyThread1 t1 = new MyThread1(task);
		t1.start();

		MyThread1 t2 = new MyThread1(task);
		t2.start();

	}

}
