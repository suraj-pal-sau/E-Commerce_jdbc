package com.irfan.threads8;

import java.util.*;
import java.util.concurrent.*;

class ReverseString implements Callable<String>{
	private String word;
	
	public ReverseString(String word)
	{
		super();
		this.word = word;
	}

	@Override
	public String call() {
		System.out.println("Reversing the String '" + word + "'  -->> " + Thread.currentThread().getName());
		reverse();
		return this.word;
	}
	
	private void reverse() {
		this.word = new StringBuilder(word).reverse().toString();
	}
}

public class CallableTask
{

	public static void main(String[] args) throws InterruptedException, ExecutionException
	{
		List<String> list = Arrays.asList("Kolkata", "Mumbai", "Delhi", "Jaipur", "Chennai", "Bhopal", "Hyderabad", "Bangalore");
		
		ExecutorService exeService = Executors.newCachedThreadPool();
		
		for(int i = 0; i < list.size(); i++) {
			
			//Callable task
			ReverseString task = new ReverseString(list.get(i));
			
			//Storing results
			Future<String> results = exeService.submit(task); 
			
			//getting results
			System.out.println("Result of Reversing : " + results.get());
			
		}
		

		exeService.shutdown();
	}

}
