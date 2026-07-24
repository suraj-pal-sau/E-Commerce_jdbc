package com.irfan.threads7;

///Whenever you're expecting any return from the thread. Runnable is not the right choice. it has run() which does'nt return result
/// right choice is Callable --> call() --> return future object (the return type of call())

import java.util.concurrent.*;

class MyCallableTask implements Callable<String>{

	@Override
	public String call() throws InterruptedException {
		Thread.sleep(2000); 	//simulating long task
		return "Task completed by " + Thread.currentThread().getName();
	}
	
}

public class CallableExample2
{

	public static void main(String[] args) throws InterruptedException, ExecutionException
	{
		ExecutorService executor = Executors.newFixedThreadPool(2);
		
		MyCallableTask task = new MyCallableTask();
		
		//submit callable task and get the future
		Future<String> future = executor.submit(task); // "executor.submit" give result(you don't know when? so result will come in future)
		
		//The Future's get() method will return the task's result upon successful completion.
		String result = future.get();	//get() --> waits until the result is ready --> Waits if necessary for the computation to complete, and then retrieves its result.
		System.out.println("Result : " + result);
		
		executor.shutdown();

	}

}
