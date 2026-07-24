package com.irfan.threads9;

//both block until result is ready
/// get() --> throws checked exception --> forces you to handle checked exceptions.
/// CompletableFuture.join() --> throws unchecked exception --> For modern asynchronous programming, CompletableFuture.join() is preferred.

import java.util.concurrent.CompletableFuture;

public class CompletableFuture1
{

	public static void main(String[] args)
	{
		System.out.println("CompletableFuture1.main()   START");
		
		//runAsync() --> not return anything
		CompletableFuture completableFuture = CompletableFuture.runAsync(() -> {
			System.out.println("this is from runAsync()");
		});
		
		//completableFuture.join();
		
		System.out.println("CompletableFuture1.main()    END");

	}

}
