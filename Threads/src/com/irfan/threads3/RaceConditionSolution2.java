package com.irfan.threads3;

class Task2
{

	synchronized void printNumber() // this method is being executed in parallel (t1 and t2) --> make synchronized
	{
		for (int i = 0; i < 10; i++)
		{

			System.out.println(" Number : " + i + " --> " + Thread.currentThread().getName());

		}
	}

}

class PrinterThread extends Thread
{

	Task2 task;

	public PrinterThread(Task2 task)
	{
		this.task = task;
	}

	@Override
	public void run()
	{
		task.printNumber();
	}
}

public class RaceConditionSolution2
{

	public static void main(String[] args)
	{
		Task2 task1 = new Task2(); // user1

		PrinterThread thread1 = new PrinterThread(task1);
		thread1.start();

//		Task2 task2 = new Task2(); // user 2	 //this is not problem --> resources different
//		PrinterThread thread2 = new PrinterThread(task2);
//		thread2.start();
		
		//problem --> one resource(task1) --> multiple threads trying to access & modify it at the exact same time
		PrinterThread thread3 = new PrinterThread(task1);
		thread3.start();
	}

}
