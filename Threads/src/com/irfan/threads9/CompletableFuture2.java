package com.irfan.threads9;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class CompletableFuture2
{

	public static void main(String[] args) throws InterruptedException, ExecutionException
	{
		System.out.println("CompletableFuture2.main()   START --> " + Thread.currentThread().getName());

		CompletableFuture<String> status = CompletableFuture.supplyAsync(() -> {
			System.out.println("this is from supplyAsync() " + Thread.currentThread().getName());
			
//			try
//			{
//				Thread.sleep(3000);
//			} catch (InterruptedException e)
//			{
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
			
			return "Success";
		});
		
		System.out.println("Response from supplyAsync() : " + status.join() );	//join() of CompletableFuture --> Returns the result value when complete

		System.out.println("CompletableFuture2.main()    END  --> " + Thread.currentThread().getName());
	}

}
