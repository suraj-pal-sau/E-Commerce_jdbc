package com.irfan.threads9;

import java.util.*;
import java.util.concurrent.CompletableFuture;

public class CompletableFutureTask1
{

	public static void main(String[] args)
	{
		List<String> list = Arrays.asList("Bangalore", "Kolkata", "Chennai", "Hyderabad");
//		
//		for(int i = 0; i < list.size(); i++) {
//			
//			CompletableFuture.supplyAsync(() -> {
//				return list.get(i);
//			}).thenApply(s -> s.chars().mapToObj(c -> (char)c).filter(ch -> list.get(i).indexOf(ch) != list.get(i).lastIndexOf(ch)))
//			  .thenAccept(ch -> System.out.println(list.get(i) + " --> " + ch));
//		
//		}
		
		///first repeating character
		String str = "Hyderabad";
			CompletableFuture.supplyAsync(() -> {
				System.out.println(Thread.currentThread().getName());
				return str;
			}).thenApply(s -> s.chars().mapToObj(c -> (char)c).filter(ch -> str.indexOf(ch) != str.lastIndexOf(ch)))
			  .thenAccept(ch -> ch.limit(1).forEach(c -> System.out.println(c)));

			//Supply two words asynchronously, transform each (uppercase/lowercase), then combine them into a sentence.
		CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> {
			System.out.println(Thread.currentThread().getName());
			try
			{
				Thread.sleep(2000);
			} catch (InterruptedException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return "Java is";
		});
		
		CompletableFuture<String> future2 = CompletableFuture.supplyAsync(() -> {
			System.out.println(Thread.currentThread().getName());
			return "Powerful.";
		});
		
		CompletableFuture<String> finalFuture = future1.thenCombineAsync(future2, (s1, s2) -> s1 + " " + s2)
				.thenApplyAsync(sentence -> sentence.toUpperCase());
		
		System.out.println("Result as A Sentence : " + finalFuture.join());
	}

}
