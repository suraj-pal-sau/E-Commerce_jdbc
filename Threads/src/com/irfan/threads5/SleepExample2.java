package com.irfan.threads5;

// disadvantage --> thread do not release the lock and go to waiting state, so all the coming thread will wait until that thread wakes up and completes its execution

class NumberPrinter1
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
						System.out.println(Thread.currentThread().getName() + " <-- going to Waiting State(sleep)");
						Thread.sleep(3000);
						System.out.println(Thread.currentThread().getName() + " <-- Waking up >> Runnable State");
					} catch (Exception e)
					{
						e.printStackTrace();
					}
				}
			}
		}
	}

}

class NumberThread1 extends Thread
{
	 NumberPrinter1 printer ;

	@Override
	public void run()
	{
		printer.printNumber();
	}
	
	
	public NumberThread1(NumberPrinter1 printer)
	{
		super();
		this.printer = printer;
	}
	
	
}


public class SleepExample2
{

	public static void main(String[] args)
	{
		NumberPrinter1 printer = new NumberPrinter1();

		NumberThread1 t1 = new NumberThread1(printer);
		t1.start();

		NumberThread1 t2 = new NumberThread1(printer);
		t2.start();

	}

}
