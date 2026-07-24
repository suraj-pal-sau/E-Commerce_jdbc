package com.irfan.threads7;

import java.util.concurrent.*;

class SquaringNumber implements Callable<Integer>{
	int number ;
	
	SquaringNumber(int num){
		this.number = num;
	}
	
	@Override
	public Integer call() throws InterruptedException {
		
		System.out.println("Calculating SQUARE of number " + number + " by --> " + Thread.currentThread().getName());
		Thread.sleep(2000);
		return number * number;
	}
	
}

public class CallableExample3
{

	public static void main(String[] args) throws InterruptedException, ExecutionException
	{
		ExecutorService executor = Executors.newFixedThreadPool(2);
		
		System.out.println("Main thread continues working...");
		
		for(int num = 1; num < 6; num++) {
			
			Future<Integer> results = executor.submit(new SquaringNumber(num));
			System.out.println("Results of Squaring :" + results.get());
			
		}
		
		executor.shutdown(); //terminates the threads
		
		System.out.println("Main thread END..." + Thread.currentThread().getName());

	}

}
