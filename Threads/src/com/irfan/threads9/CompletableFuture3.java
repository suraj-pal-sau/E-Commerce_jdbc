package com.irfan.threads9;

import java.util.concurrent.CompletableFuture;

public class CompletableFuture3
{

	public static void main(String[] args)
	{
		CompletableFuture<String>  cf = CompletableFuture.supplyAsync(() -> {
			String name = "Kodewala --> " + Thread.currentThread().getName();
			
//			try
//			{
//				Thread.sleep(2000);
//			} catch (InterruptedException e)
//			{
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
			
			return name;
		}).thenApply(s -> s.toUpperCase() + " " + Thread.currentThread().getName());	//thenApply() --> same thread of supplyAsync() will do this action
		
		System.out.println("upperCase operation after response1 : " + cf.join());
		
		System.out.println();System.out.println();
		
//		CompletableFuture<String>  cf1 = CompletableFuture.supplyAsync(() -> {
//			String name = "Kodewala --> " + Thread.currentThread().getName();
//			System.out.println("input: " + name);
//			return name;
//		}).thenApplyAsync(s -> s.toUpperCase() + " - " + Thread.currentThread().getName() + " ");	// if you want that other thread do apply operation then use --> thenApplyAsync
//		
//		System.out.println("upperCase operation after response2 : " + cf1.join());

		
		
		System.out.println();System.out.println();System.out.println();
		
		CompletableFuture  cf2 = CompletableFuture.supplyAsync(() -> {
			String name = "Kodewala ";
			System.out.println("input: " + name);
			return name;
		}).thenAccept(s -> System.out.println("Accept " + s));	
		
		System.out.println(" response : " + cf2.join());
		
	}

}

