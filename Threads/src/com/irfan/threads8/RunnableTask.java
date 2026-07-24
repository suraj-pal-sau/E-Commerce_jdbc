package com.irfan.threads8;

import java.util.concurrent.*;

class Task implements Runnable
{

	public void run()
	{
		System.out.println("Task.run() --> started Execution of the task -> " + Thread.currentThread().getName());
	}
}

public class RunnableTask
{

	public static void main(String[] args) throws InterruptedException, ExecutionException
	{
		Task task = new Task();
		
		ExecutorService executor = Executors.newCachedThreadPool();
		
		///run has void return type --> storing its results in future object --> null if using submit() on runnable task
//		Future result1 = executor.submit(task);	//no error but future.get() --> give null
//		System.out.println("Result --> " + result1.get());
		
		///use execute for runnable 
		//Future result = executor.execute(task);	//Error : Type mismatch: cannot convert from void to Future
		
		executor.execute(task);
		
		executor.shutdown();
		
	}

}
