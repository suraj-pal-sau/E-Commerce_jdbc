package com.irfan.threads2;

class NewThread extends Thread
{

	@Override
	public void run()
	{	// RUNNING --> PICKED BY CPU AND THREAD IS EXECUTING ITS run() METHOD
		System.out.println("RUNNING THREAD BY EXTENDING THREAD CLASS...");
		
		
		try
		{	// WAITING --> TEMPORARILY PAUSED, OR WAITING TO BE NOTIFIED
			System.out.println("WAITING......2sec");
			Thread.sleep(2000);
		} catch (InterruptedException e)
		{
			e.printStackTrace();
		}
		
		Calculator calc = new Calculator();
		calc.add();
	} // TERMINATED --> THREAD COMPLETES run() METHOD

}

class Calculator
{
	public void add() {
		System.out.println("Addition by : " + Thread.currentThread().getName());
	}
}

public class LifeCycleOfThread4
{

	public static void main(String[] args)
	{
		System.out.println("LifeCycleOfThread4.main()....." + Thread.currentThread().getName());
		
		NewThread thread = new NewThread();	//NEW --> A THREAD IS CREATED BUT NOT STARTED
		thread.start();	//RUNNABLE --> THREAD IS READY TO RUN AND WAITING FOR THE CPU TIME

	}

}
