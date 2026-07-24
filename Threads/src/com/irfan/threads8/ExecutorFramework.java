package com.irfan.threads8;

import java.util.concurrent.*;

class NotifyDelivery implements Runnable{
	
	public void run() {
		System.out.println("NotifyDelivery.run()...STARTED " + Thread.currentThread().getName());
		try
		{
			Thread.sleep(5000);
		} catch (InterruptedException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	System.out.println("NotifyDelivery.run()...ENDED " + Thread.currentThread().getName());
		
	}
}

public class ExecutorFramework
{

	public static void main(String[] args)
	{
	//	ExecutorService executor = Executors.newSingleThreadExecutor();	// will create only single thread
		
		///dynamically --> no of threads will be decided based on no. of tasks
		ExecutorService executor = Executors.newCachedThreadPool(); // number of thread will be decided by executor framework
		
		//ExecutorService executor = Executors.newFixedThreadPool(10);	//fix number of threads will be created by exec. framework
		
		for(int i = 0; i< 11; i++) {
			NotifyDelivery notifyTask = new NotifyDelivery();
			
			//for runnable use execute()
			executor.execute(notifyTask);
		}
		
		
		executor.shutdown();

	}

}
