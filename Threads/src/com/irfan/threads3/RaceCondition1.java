package com.irfan.threads3;

// when multiple threads trying to access and modify the same shared resources(variables, object.) at the same time without proper control, then 
// it is race condition, which leads to data inconsistency, wrong final result
//Solution : synchronized keyword

class MyThread extends Thread
{
	Task task;

	public MyThread(Task task)
	{
		this.task = task;
	}

	public void run()
	{
		System.out.println("Setting up the payment pathways... --> " + Thread.currentThread().getName());
		task.doTask();
	}
}

class Task
{

	public void doTask()
	{
		System.out.println("Starting payment process... --> " + Thread.currentThread().getName());
		Payment p = new Payment();
		p.doPaymet();
	}
}

class Payment
{
	// multiple threads trying to do payment at the same time using same bank
	// account --> example : one person is doing payment through phonePe, that
	// person's debit card is being used by its relative anywhere but same time
	// race condition --> bank will loose money
	public void doPaymet()
	{
		System.out.println("Payment.doPaymet()....doing payment....--> " + Thread.currentThread().getName());
	}
}

public class RaceCondition1
{

	public static void main(String[] args)
	{
		// shared resources
		Task task = new Task();

		// multiple threads trying for accessing and modifying the same resources
		// concurrently
		MyThread t1 = new MyThread(task);
		t1.start();

		MyThread t2 = new MyThread(task);
		t2.start();
	}

}
