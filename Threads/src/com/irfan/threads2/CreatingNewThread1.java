package com.irfan.threads2;

class NewwThread implements Runnable {
	
	public void run() {
		System.out.println("Running thread by implementing RUNNABLE Interface....");
		System.out.println(Thread.currentThread().getName());
	}
}

public class CreatingNewThread1
{

	public static void main(String[] args) throws InterruptedException
	{
		System.out.println("CreatingNewThread1.main()....1...."+ Thread.currentThread().getName());
		
		//object of your thread class
		NewwThread thread = new NewwThread();
		
		//pass object as argument in Thread constructor
		Thread newThread = new Thread(thread);	//NEW --> Thread is created
		
		//newThread.setName("MyNewThread-0");
		
		newThread.start();
		
		Thread.sleep(1000);
		
		System.out.println("CreatingNewThread1.main()....2...."+ Thread.currentThread().getName());
		
		// it is working because 'main thread' executing first and completed running main method first
		newThread.setName("MyNewThread-0");	//but when I paused main thread temporarily for 1 sec, it is not working(thread name not set) 
											//because new thread got the cpu and executed the run() method	
	}

}
