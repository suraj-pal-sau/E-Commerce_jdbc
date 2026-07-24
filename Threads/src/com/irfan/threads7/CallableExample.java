package com.irfan.threads7;

/// in java5 --> run() limitation solved by Callable's call() which return computed result and @throws Exception if unable to compute a result
/// before java5 --> run() status must be stored in database/file-system or somewhere else we have to store it and if needed then check the database
/// 			-->	thread does not return anything directly to main method, so thread will write something to database 
/// java5 --> thread can be reused --> threads never dies --> Executor FrameWork --> API 
///in case of "runnable interface / thread class" thread cannot be restarted  --> once dies/terminates --> IllegalThreadStateException

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

class EmailSender implements Callable<Boolean>
{

	public Boolean call()
	{
		System.out.println("EmailSender.call() -> "  + Thread.currentThread().getName());
		return true;
	}
}

class SendingMessage implements Callable<String>
{

	public String call()
	{
		System.out.println("SendingMessage.call() -> " + Thread.currentThread().getName());
		return "Sent";
	}
}

public class CallableExample
{

	public static void main(String[] args) throws InterruptedException, ExecutionException
	{
	///	IllegalArgumentException - if nThreads <= 0
		ExecutorService es = Executors.newFixedThreadPool(3);

		EmailSender task = new EmailSender();
		SendingMessage task2 = new SendingMessage();

		for (int i = 0; i < 5; i++)
		{
			Future<Boolean> response = es.submit(task);
			System.out.println("response from call() method is : " + response.get());

			Future<String> response1 = es.submit(task2);
			System.out.println("response from call() method is : " + response1.get());
		}

		es.shutdown();	//The threads in the pool will exist until it is explicitly shutdown.
	}

}
