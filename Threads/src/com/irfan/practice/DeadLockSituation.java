package com.irfan.practice;
/// A deadlock occurs when two or more threads are permanently blocked because each is waiting for a resource held by another.
/// Program never finishes --> It hangs forever

public class DeadLockSituation
{
	private static Object lock1 = new Object();
	private static Object lock2 = new Object();

	public static void main(String[] args)
	{
		//t1
		Thread t1 = new Thread(() -> {

			synchronized (lock1)
			{
				System.out.println(Thread.currentThread().getName() + " got Lock1");

				try
				{
					Thread.sleep(2000);
				} catch (InterruptedException e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				System.out.println(Thread.currentThread().getName() + " holding Lock1 and waiting for Lock2..............");
				
				synchronized (lock2)
				{
					System.out.println(Thread.currentThread().getName() + " got Lock2");
				}
			}
		});

		//t2
		Thread t2 = new Thread(() -> {

			synchronized (lock2)
			{
				System.out.println(Thread.currentThread().getName() + " got Lock2");

				try
				{
					Thread.sleep(2000);
				} catch (InterruptedException e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println(Thread.currentThread().getName() + " holding Lock2 and waiting for Lock1.............");
				
				synchronized (lock1)
				{
					System.out.println(Thread.currentThread().getName() + " got Lock1");
				}
			}
		});
		
		t1.start();
		t2.start();
		

	}

}
