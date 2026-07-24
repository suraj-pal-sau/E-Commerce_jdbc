package com.irfan.threads8;

//sum of any integer collection 

import java.util.*;
import java.util.concurrent.*;

class Sum implements Callable<Integer>
{
	Collection<Integer> collection;
	int sum;
	
	public Sum(Collection<Integer> collection)
	{
		super();
		this.collection = collection;
	}



	@Override
	public Integer call() throws InterruptedException
	{
		System.out.println("Calculating sum... ");
		sum = collection.stream().reduce(0, (e1, e2) -> e1 + e2);
		Thread.sleep(2000); //taking time for the sum
		return sum;
	}
}

public class CallableTask2
{

	public static void main(String[] args) throws InterruptedException, ExecutionException
	{
		List<Integer> list = Arrays.asList(2,6,3,8,12,99,100);
		Set<Integer> set = new HashSet<>(list);
		set.add(200); set.add(500);
		
		ExecutorService executor = Executors.newCachedThreadPool();
		
		//Sum task1 = new Sum(list);
		Sum task2 = new Sum(set);
		
		Future<Integer> result1 = executor.submit(task2);
		System.out.println("Result as Sum of elements in a Collection : " + result1.get());
		
		
		executor.shutdown();

	}

}
