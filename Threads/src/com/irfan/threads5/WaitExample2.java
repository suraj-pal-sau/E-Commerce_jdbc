package com.irfan.threads5;

//advantage --> release the lock, so the coming thread thread will execute 
//IllegalMonitorStateException --> in static method 

class NumberPrinter
{
	// private final Object lock = new Object(); // instance variable
	
	public void printNumber()
	{
		System.out.println("other logics........100 lines of code --> " + Thread.currentThread().getName());

		//synchronized (lock)
		synchronized (this)
		{

			for (int i = 0; i < 21; i++)
			{

				System.out.println("Number is " + i + " printed by --> " + Thread.currentThread().getName());

				if (i == 10)
				{

					try
					{
						System.out.println(Thread.currentThread().getName() + " <-- going to Waiting State");
						//lock.wait(100);
						wait(3000);
						System.out.println(Thread.currentThread().getName() + " <-- Waking up, Acquired the Lock -> Runnable State");
					} catch (Exception e)
					{
						e.printStackTrace();
					}
				}
			}
		}
	}

}

class NumberThread extends Thread
{
	 NumberPrinter printer ;

	@Override
	public void run()
	{
		printer.printNumber();
	}

	public NumberThread(NumberPrinter printer)
	{
		super();
		this.printer = printer;
	}
	
	
}

public class WaitExample2
{

	public static void main(String[] args)
	{
		NumberPrinter printer = new NumberPrinter();

		NumberThread t1 = new NumberThread(printer);
		t1.start();

		NumberThread t2 = new NumberThread(printer);
		t2.start();
	}

}
