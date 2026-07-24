package com.irfan.threads7;

import java.util.concurrent.*;

class PrimeCheck implements Callable<String>
{
	int number;

	public PrimeCheck(int number)
	{
		super();
		this.number = number;
	}

	@Override
	public String call() throws InterruptedException
	{
		System.out.println("Checking if the Number " + number + " is Prime --> " + Thread.currentThread().getName());
		Thread.sleep(1000); // checking time
		return isPrime(number) ? "Prime" : "Not Prime";
	}

	private boolean isPrime(int num)
	{
		if (num <= 1)
			return false;

		for (int i = 2; i < Math.sqrt(num); i++)
		{
			if (num % i == 0)
				return false;
		}
		return true;
	}
}

public class CallableExample4
{

	public static void main(String[] args) throws Exception
	{
		ExecutorService executor = Executors.newFixedThreadPool(2);

		for (int i = 0; i < 15; i++)
		{

			Future<String> futureResult = executor.submit(new PrimeCheck(i));
			System.out.println("Prime Check Result : " + futureResult.get());

		}

		executor.shutdown();
	}

}
