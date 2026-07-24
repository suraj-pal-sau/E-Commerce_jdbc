package com.irfan.practice;

///Method 1: Always Acquire Locks in the Same Order ⭐⭐⭐⭐⭐ (Most Recommended) 
/// 	Make sure that every thread acquire locks in the same order --> thread1 (lock1, lock2....), thread2(lock1, lock2)  or thread1(lock2, lock1), thread2(lock2,lock1)

///Method 2: Use ReentrantLock.tryLock()
/// 	Why is this useful?  -->	Unlike synchronized, tryLock() doesn't force a thread to wait forever.

///Method 3: Use Timed tryLock() --> wait for only limited time
/// Method 4: Avoid Nested Locks When Possible

public class AvoidDeadLock
{
	private static Object lock1 = new Object();
	private static Object lock2 = new Object();

	public static void main(String[] args) throws InterruptedException
	{
		///same order --> nested locks --> all threads --> lock1, lock2.....
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
						System.out.println(Thread.currentThread().getName() + " holding Lock1 and waiting for Lock2.............");
						
						synchronized (lock2)
						{
							System.out.println(Thread.currentThread().getName() + " got Lock2");
						}
					}
				});
				
				t1.start();
				t2.start();
				
				t1.join();
				t2.join();
				System.out.println("NO DEADLOCK");

	}

}
